package fr.spc.leosoliveres.chaldeas.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.room.*

data class FamilyWithMeasures(
	@Embedded val family: Family,
	@Relation (
		parentColumn = "familyId",
		entityColumn = "containingFamilyId"
	) val measures : List<Measure>
)

@Entity(tableName="measures")
data class Measure(
	@PrimaryKey(autoGenerate = true) val measureId:Long=0,
	@ColumnInfo(name="containingFamilyId")var familyId:Long=0,
	var name:String,
	var unitFull:String,
	var unitAbriged:String
){
	constructor(
		_name:String="Nom non défini",
		_unitFull:String="Unité non définie",
		_unitAbriged:String="Indéf."
	):this(0,0,_name,_unitFull,_unitAbriged)

	constructor(
		_id:Long,
		_name:String="Nom non défini",
		_unitFull:String="Unité non définie",
		_unitAbriged:String="Indéf."
	):this(0,_id,_name,_unitFull,_unitAbriged)

	init {
		if(name.isEmpty() || name.isBlank()) name = "Nom non défini"
		if(unitFull.isEmpty() || unitFull.isBlank()) unitFull = "Unité non définie"
		if(unitAbriged.isEmpty() || unitAbriged.isBlank()) unitAbriged = "Indéf."
	}

	fun allUnits():String{
		return "$unitFull - $unitAbriged"
	}

	fun setParentId(newId:Long) {
		this.familyId = newId
	}

	override fun toString():String {
		return "$name - $unitFull - $unitAbriged"
	}
}

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