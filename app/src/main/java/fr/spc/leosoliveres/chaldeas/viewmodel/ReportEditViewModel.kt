package fr.spc.leosoliveres.chaldeas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.spc.leosoliveres.chaldeas.model.Measure

class ReportEditViewModel() : ViewModel() {
	private var _measures = MutableLiveData<List<Measure>>()
	val measures : LiveData<List<Measure>>
		get() = _measures

	init {
		_measures.value = initVariables()
	}

	fun initVariables(family:String="Faisceau 1",count:Int=20):ArrayList<Measure>{
		val arrayList = ArrayList<Measure>()
		for(i in 0..count){
			arrayList.add(
				Measure(
					String.format("Mesure n°$i - famille $family"),
					String.format("Unité $i"),
					String.format("U$i")
				)
			)
		}
		return arrayList
	}
}