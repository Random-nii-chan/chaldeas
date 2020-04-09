package fr.spc.leosoliveres.chaldeas.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Measure(
	@PrimaryKey (autoGenerate = true) val uid:Int=0,
	@ColumnInfo (name="Name") val name:String,
	@ColumnInfo (name="UnitFull") val unitFull:String,
	@ColumnInfo (name="UnitAbriged") val unitAbriged:String
){
	constructor(_name:String,_unitFull:String,_unitAbriged:String):this(0,_name,_unitFull,_unitAbriged)

	fun allUnits():String{
		return String.format("$unitFull ($unitAbriged)")
	}
}