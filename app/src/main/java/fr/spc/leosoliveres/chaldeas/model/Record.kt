package fr.spc.leosoliveres.chaldeas.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName="Records")
data class Record(
	@PrimaryKey(autoGenerate = true) @ColumnInfo(name="record_id") val id:Long,
	val reportId:Long,

	val family:String,

	val name:String,
	val unitFull:String,
	val unitAbriged:String,
	var value:Float
) {
	@Ignore
	private val exportedString:String = if(value.isNaN()) "non renseigné" else value.toString()
	fun export():String =  "$name : $exportedString $unitAbriged"
}