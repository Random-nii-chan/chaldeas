package fr.spc.leosoliveres.chaldeas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.spc.leosoliveres.chaldeas.model.Family
import fr.spc.leosoliveres.chaldeas.model.Measure

class ReportEditViewModel() : ViewModel() {

	//TODO: Faire une classe pour un regroupement de familles ?

	private var _familyList = MutableLiveData<ArrayList<Family>>()
	val familyList : LiveData<ArrayList<Family>>
		get() = _familyList

	private var currentFamilyIndex:Int=0

	private var _currentFamily = MutableLiveData<Family>()
	val currentFamily : LiveData<Family>
			get() = _currentFamily

	private var _measures = MutableLiveData<ArrayList<Measure>>()
	val measures : LiveData<ArrayList<Measure>>
		get() = _measures

	init {
		_familyList.value = initFamilies()
		_currentFamily.value = _familyList.value!![currentFamilyIndex]
		_measures.value = _currentFamily.value!!.measures
	}

	fun editMeasure(m:Measure,newData:Measure) {
		val index = _measures.value?.indexOf(m)
		val temporaryList = _measures.value
		if (index != null) temporaryList?.set(index,newData)
		//besoin d'assigner une valeur pour déclencher l'évènement d'observations
		_measures.value = temporaryList
	}

	fun deleteMeasure(m:Measure) {
		val tempList = _measures.value
		tempList?.remove(m)
		_measures.value = tempList
	}

	fun duplicateMeasure(m:Measure) {
		val tempList = _measures.value
		tempList?.add(Measure("Copie de ${m.name}",m.unitFull,m.unitAbriged))
		_measures.value = tempList
	}

	fun addMeasure(m:Measure) {
		val tempList = _measures.value
		tempList?.add(m)
		_measures.value = tempList
	}

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