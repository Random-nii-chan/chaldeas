package fr.spc.leosoliveres.chaldeas.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

//enregistrement = élément d'un rapport
//relevé enregistré par l'utilisateur dans le processus de création d'un rapport
@Entity(tableName="Records")
data class Record(
	@PrimaryKey(autoGenerate = true) @ColumnInfo(name="record_id") val id:Long,
	//TODO passer en clé primaire ?
	var reportId:Long,

	val family:String,

	val name:String,
	val unitFull:String,
	val unitAbridged:String,
	var value:String
) {
	//annotation room pour ne pas enregistrer l'attribut dans la BDD
	@Ignore
	private val exportedString:String = if(value.isEmpty()) "non renseigné" else value
	fun export():String =  "$name : $exportedString $unitAbridged"
}