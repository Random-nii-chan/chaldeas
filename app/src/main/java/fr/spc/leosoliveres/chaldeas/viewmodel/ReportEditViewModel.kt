package fr.spc.leosoliveres.chaldeas.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.spc.leosoliveres.chaldeas.model.Family
import fr.spc.leosoliveres.chaldeas.model.Measure
import fr.spc.leosoliveres.chaldeas.model.PropertyAwareMutableLiveData
import fr.spc.leosoliveres.chaldeas.model.database.AppDatabase

class ReportEditViewModel(ctx: Context) : ViewModel() {

	private var _familyList = MutableLiveData<ArrayList<Family>>()
	val familyList : LiveData<ArrayList<Family>>
		get() = _familyList

	private var currentFamilyIndex : Int = 0

	private var _currentFamily = PropertyAwareMutableLiveData<Family>()
	val currentFamily : LiveData<Family>
		get() = _currentFamily

	private val db:AppDatabase =
		Room.databaseBuilder(ctx,AppDatabase::class.java,"chaldeas-database").build()

	init {
		_familyList.value = initFamilies()
		_currentFamily.value = _familyList.value!![currentFamilyIndex]
	}

	fun getFamilyIndex():Int = currentFamilyIndex

	fun changeFamily(i:Int) {
		val maxValue = _familyList.value!!.size -1
		currentFamilyIndex = if(i in 0..maxValue) i else maxValue
		_currentFamily.value = _familyList.value!![currentFamilyIndex]
	}

	fun familiesToString():ArrayList<String> {
		val al = ArrayList<String>()
		for(i in 0 until _familyList.value!!.size) al.add(_familyList.value!![i].toString())
		return al
	}

	//Méthodes CRUD mesures
	fun editMeasure(m:Measure,newData:Measure) {
		val tempList = _currentFamily.value?.measures
		val index = tempList?.indexOf(m)
		if (index != null) tempList[index] = newData
		//besoin d'assigner une valeur pour déclencher l'évènement d'observations
		_currentFamily.value!!.measures = tempList!!
	}

	fun deleteMeasure(m:Measure) {
		_currentFamily.value!!.removeMeasure(_currentFamily.value!!.getIndex(m))
	}

	fun duplicateMeasure(m:Measure) {
		val tempList = _currentFamily.value?.measures
		tempList?.add(Measure("Copie de ${m.name}",m.unitFull,m.unitAbriged))
		_currentFamily.value!!.measures = tempList!!
	}

	fun addMeasure(m:Measure) {
		val tempList = _currentFamily.value!!.measures
		tempList.add(m)
		_currentFamily.value!!.measures = tempList
	}

	//Méthodes CRUD Familles
	fun renameFamily(n:String) {
		_currentFamily.value!!.name = n
	}

	fun addFamily(f:Family) {
		val tempList = _familyList.value
		tempList!!.add(f)
		_familyList.value = tempList
		currentFamilyIndex = _familyList.value!!.size-1
	}

	fun deleteFamily(f:Family) {
		val tempList = _familyList.value
		tempList!!.remove(f)
		_familyList.value = tempList
		currentFamilyIndex--
	}

	//initialisations
	private fun initFamilies(count:Int=3):ArrayList<Family> {
		val families = ArrayList<Family>()
		for(i in 0..count) families.add(Family("Famille n°$i",initMeasures()))
		return families
	}

	private fun initMeasures(count:Int=5):ArrayList<Measure>{
		val arrayList = ArrayList<Measure>()
		for(i in 0..count) arrayList.add(Measure("Mesure n°$i", "Unité $i", "U$i"))
		return arrayList
	}
}