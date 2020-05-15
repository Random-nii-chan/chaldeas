package fr.spc.leosoliveres.chaldeas.model.entity

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "families")
data class Family(private val _name:String) : BaseObservable() {

	//var et non private parce que Room ne fonctionne pas
	@PrimaryKey(autoGenerate = true) var familyId:Long = 0

	@Bindable
	@ColumnInfo(name="Name") var name : String = _name
	set(value) {
		field = value
		notifyPropertyChanged(BR.name)
	}

	@Bindable
	@Ignore
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

	fun addMeasure(m: Measure) {
		val tempList = measures
		m.setParentId(this.familyId)
		tempList.add(m)
		measures = tempList
	}

	fun getIndex(m: Measure):Int {
		return if(measures.contains(m))
			measures.indexOf(m)
		else
			-1
	}

	fun size():Int {
		return measures.size
	}

	override fun toString():String {
		return name
	}
}