package fr.spc.leosoliveres.chaldeas.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.spc.leosoliveres.chaldeas.model.Family
import fr.spc.leosoliveres.chaldeas.model.Measure
import fr.spc.leosoliveres.chaldeas.model.dao.FamilyDao
import fr.spc.leosoliveres.chaldeas.model.dao.MeasureDao

@Database(entities = [Measure::class,Family::class], version=1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
	//TODO objet base de données
	abstract fun measureDao(): MeasureDao
	abstract fun familyDao(): FamilyDao

	private var instance : AppDatabase?=null

	fun getInstance(context: Context): AppDatabase? {
		if (instance == null) {
			synchronized(AppDatabase::class) {
				instance = Room.databaseBuilder(
					context.applicationContext,
					AppDatabase::class.java, "notes_database"
				)
					.fallbackToDestructiveMigration()
					.addCallback(roomCallback)
					.build()
			}
		}
		return instance
}