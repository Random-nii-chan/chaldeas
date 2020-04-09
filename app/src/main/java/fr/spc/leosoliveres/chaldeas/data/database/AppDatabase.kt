package fr.spc.leosoliveres.chaldeas.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.*
import androidx.room.RoomDatabase
import fr.spc.leosoliveres.chaldeas.data.dao.MeasureDao
import fr.spc.leosoliveres.chaldeas.model.Measure

//TODO : Ajouter des classes pour ajouter des tables
@Database(entities = [Measure::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
	abstract fun measureDao(): MeasureDao

	companion object {
		private var INSTANCE: AppDatabase?=null
		fun getInstance(context: Context): AppDatabase{
			if(INSTANCE == null) {
				INSTANCE = databaseBuilder(
					context,
					AppDatabase::class.java,
				"roomdb"
				).build()
			}
			return INSTANCE as AppDatabase
		}
	}
}