package fr.spc.leosoliveres.nightly.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName="Records")
data class Record(
	@PrimaryKey(autoGenerate = true) @ColumnInfo(name="record_id") val id:Long,
	var reportId:Long,

	val family:String,

	val name:String,
	val unitFull:String,
	val unitAbridged:String,
	var value:String
) {
	@Ignore
	private val exportedString:String = if(value.isEmpty()) "non renseigné" else value
	fun export():String =  "$name : $exportedString $unitAbridged"
}