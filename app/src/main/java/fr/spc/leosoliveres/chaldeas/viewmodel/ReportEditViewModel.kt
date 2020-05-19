package fr.spc.leosoliveres.chaldeas.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.spc.leosoliveres.chaldeas.model.entity.Family
import fr.spc.leosoliveres.chaldeas.model.entity.Measure
import fr.spc.leosoliveres.chaldeas.model.entity.PropertyAwareMutableLiveData
import fr.spc.leosoliveres.chaldeas.model.dao.FamilyDao
import fr.spc.leosoliveres.chaldeas.model.dao.MeasureDao
import fr.spc.leosoliveres.chaldeas.model.database.AppDatabase
import fr.spc.leosoliveres.chaldeas.model.repository.AppRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReportEditViewModel(ctx: Context) : ViewModel() {

	var familyList : LiveData<List<Family>>

	var currentFamily = PropertyAwareMutableLiveData<Family>()

	private var currentFamilyIndex : Int = 0

	private val repository: AppRepo

	private val fDao:FamilyDao = AppDatabase.getDatabase(ctx,viewModelScope).familyDao()
	private val mDao: MeasureDao = AppDatabase.getDatabase(ctx,viewModelScope).measureDao()

	init {
		repository = AppRepo(fDao)
		familyList = repository.families
		//_familyList.value = initFamilies()
		Log.i("value",familyList.value.toString())
		if(familyList.value!!.isNullOrEmpty()) currentFamily.value = Family("")
		else currentFamily.value = familyList.value!![currentFamilyIndex]
	}

	fun getFamilyIndex():Int = currentFamilyIndex

	fun changeFamily(i:Int) {
		if(familyList.value!!.isNotEmpty()) {
			val maxValue = (familyList.value!!.size-1)
			currentFamilyIndex = if (i in 0..maxValue) i else maxValue
			currentFamily.value = familyList.value!![currentFamilyIndex]
		}
		else {
			currentFamilyIndex = 0
			currentFamily.value = Family("")
		}
	}

	fun familiesToString():ArrayList<String> {
		val al = ArrayList<String>()
		for(element in familyList.value!!) al.add(element.toString())
		return al
	}

	//Méthodes CRUD mesures
	fun editMeasure(m: Measure, newData: Measure) {
		val tempList = currentFamily.value?.measures
		val index = tempList?.indexOf(m)
		if (index != null) tempList[index] = newData
		//besoin d'assigner une valeur pour déclencher l'évènement d'observations
		currentFamily.value!!.measures = tempList!!
	}

	fun deleteMeasure(m: Measure) = viewModelScope.launch(Dispatchers.IO) {
		mDao.delete(m.measureId)
	}

	fun duplicateMeasure(m: Measure) = viewModelScope.launch(Dispatchers.IO) {
		mDao.insert(
			Measure(
				"Copie de ${m.name}",
				m.unitFull,
				m.unitAbriged
			)
		)
	}

	fun addMeasure(m: Measure) = viewModelScope.launch(Dispatchers.IO){
		mDao.insert(m)
	}

	//Méthodes CRUD Familles
	fun updateFamilyName() = viewModelScope.launch(Dispatchers.IO) {
		fDao.update(currentFamily.value!!)
		//currentFamily.value!!.name = n
	}

	fun addFamily(f: Family) = viewModelScope.launch(Dispatchers.IO) {
		fDao.insertOne(f)
		/*
		val tempList = familyList.value
		tempList!!.add(f)
		familyList.value = tempList
		currentFamilyIndex = familyList.value!!.size-1
		 */
	}

	fun deleteFamily(f: Family) = viewModelScope.launch(Dispatchers.IO) {
		fDao.deleteOne(f.familyId)
		/*
		val tempList = familyList.value
		tempList!!.remove(f)
		familyList.value = tempList
		currentFamilyIndex--
		*/
	}

	//initialisations
	private fun initFamilies(count:Int=3):ArrayList<Family> {
		val families = ArrayList<Family>()
		for(i in 0..count) families.add(
			Family(
				"Famille n°$i",
				initMeasures()
			)
		)
		return families
	}

	private fun initMeasures(count:Int=5):ArrayList<Measure>{
		val arrayList = ArrayList<Measure>()
		for(i in 0..count) arrayList.add(
			Measure(
				"Mesure n°$i",
				"Unité $i",
				"U$i"
			)
		)
		return arrayList
	}
}