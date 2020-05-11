package fr.spc.leosoliveres.chaldeas.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="measures")
data class Measure(
	@PrimaryKey (autoGenerate = true) @ColumnInfo(name="MeasureId") val measureId:Int=0,
	@ColumnInfo (name="Name") var name:String,
	@ColumnInfo (name="UnitFull") var unitFull:String,
	@ColumnInfo (name="UnitAbriged") var unitAbriged:String
){
	constructor(_name:String="Nom non défini",_unitFull:String="Unité non définie",_unitAbriged:String="Indéf."):this(0,_name,_unitFull,_unitAbriged)

	init {
		if(name.isEmpty() || name.isBlank()) name = "Nom non défini"
		if(unitFull.isEmpty() || unitFull.isBlank()) unitFull = "Unité non définie"
		if(unitAbriged.isEmpty() || unitAbriged.isBlank()) unitAbriged = "Indéf."
	}

	fun allUnits():String{
		return "$unitFull - $unitAbriged"
	}

	override fun toString():String {
		return "$name - $unitFull - $unitAbriged"
	}
}