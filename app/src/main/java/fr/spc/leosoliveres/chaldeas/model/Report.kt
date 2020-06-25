package fr.spc.leosoliveres.chaldeas.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//Rapport créé par l'utilisateur
//ne comprend pas une liste d'enregistrements : cf reportwithrecords
//TODO modifier la vue sitedetailfragment pour ajouter un formulaire qui édite les champs du rapport (agents, date, commentaire, etc)
@Entity(tableName="Reports")
data class Report(
	@PrimaryKey(autoGenerate = true) @ColumnInfo(name="report_id") val id:Long=0,
	val siteId:Long,
	//string qui contient le nom des agents
	val agents:String,
	val date: Date,
	val comment:String,
	val gravity:Int
) {
	fun summary():String = "$date ($agents)"
}