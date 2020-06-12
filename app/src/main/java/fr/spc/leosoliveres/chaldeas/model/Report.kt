package fr.spc.leosoliveres.chaldeas.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName="Reports")
data class Report(
	@PrimaryKey(autoGenerate = true) @ColumnInfo(name="report_id") val id:Long,
	val siteId:Long,
	val agents:String,
	val date: Date,
	val comment:String,
	val gravity:Int
) {
	fun summary():String = "$date ($agents)"
}