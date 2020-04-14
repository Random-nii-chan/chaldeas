package fr.spc.leosoliveres.chaldeas.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.spc.leosoliveres.chaldeas.model.Measure

class ReportEditViewModel() : ViewModel() {
	private var _measures = MutableLiveData<MutableList<Measure>>()
	val measures : LiveData<MutableList<Measure>>
		get() = _measures

	init {
		_measures.value = initVariables()
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

	fun initVariables(count:Int=5):ArrayList<Measure>{
		val arrayList = ArrayList<Measure>()
		for(i in 0..count){
			arrayList.add(
				Measure(
					String.format("Mesure n°$i"),
					String.format("Unité $i"),
					String.format("U$i")
				)
			)
		}
		return arrayList
	}
}