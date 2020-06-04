package fr.spc.leosoliveres.chaldeas.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.google.gson.annotations.Expose

data class Family(private val _name:String) : BaseObservable() {
	@Bindable
	@Expose
	var name : String = _name
	set(value) {
		field = value
		notifyPropertyChanged(BR.name)
	}

	@Bindable
	@Expose
	var measures : ArrayList<Measure> = ArrayList()
	set(value) {
		field=value
		notifyPropertyChanged(BR.measures)
	}

	constructor(_name:String, _measures:ArrayList<Measure>) : this(_name) {
		name = _name
		measures = _measures
	}

	fun removeMeasure(i:Int) {
		val tempList = measures
		tempList.removeAt(i)
		measures = tempList
	}

	fun getIndex(m:Measure):Int {
		return if(measures.contains(m))
			measures.indexOf(m)
		else
			-1
	}

	fun measureCount():Int {
		return measures.size
	}

	override fun toString():String {
		return name
	}
}