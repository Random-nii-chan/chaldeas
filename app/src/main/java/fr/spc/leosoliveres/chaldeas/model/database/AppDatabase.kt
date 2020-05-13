package fr.spc.leosoliveres.chaldeas.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import fr.spc.leosoliveres.chaldeas.model.Family
import fr.spc.leosoliveres.chaldeas.model.Measure
import fr.spc.leosoliveres.chaldeas.model.dao.FamilyDao
import fr.spc.leosoliveres.chaldeas.model.dao.MeasureDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Measure::class,Family::class], version=1, exportSchema = true)
abstract class AppDatabase:RoomDatabase() {
	abstract fun familyDao(): FamilyDao
	abstract fun measureDao(): MeasureDao

	companion object {
		//defining a singleton
		@Volatile
		//instance of the singleton
		private var INSTANCE : AppDatabase?=null
		//method getting the instance of the database
		fun getDatabase(
			context: Context,
			scope: CoroutineScope
		):AppDatabase {
			val tempInstance = INSTANCE
			//if not null, returning it
			if(tempInstance != null) {
				return tempInstance
			}
			//else creating a new instance, assinging it and returning it
			synchronized(this) {
				val instance = Room.databaseBuilder(
					context.applicationContext,
					AppDatabase::class.java,
					"word_database"
				).build() //build AFTER adding callback
				INSTANCE = instance
				return instance
			}
		}
	}
}