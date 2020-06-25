package fr.spc.leosoliveres.chaldeas.model.database

import androidx.room.TypeConverter
import java.util.*

//convertisseurs automatiques pour room
//transformer des types complexes en types primitifs reconnaissables par SQLite (room)
class Converters {
	//long -> date
	@TypeConverter
	fun fromTimestamp(value: Long?): Date? {
		return value?.let { Date(it) }
	}
	//date -> long
	@TypeConverter
	fun dateToTimestamp(date: Date?): Long? {
		return date?.time?.toLong()
	}
}