package fr.spc.leosoliveres.chaldeas.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName="measures",foreignKeys = [ForeignKey(
	entity = Family::class,
	parentColumns = ["familyId"],
	childColumns = ["containingFamilyId"],
	onDelete = ForeignKey.CASCADE
)])
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