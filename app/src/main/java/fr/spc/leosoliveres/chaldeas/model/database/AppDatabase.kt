package fr.spc.leosoliveres.chaldeas.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.spc.leosoliveres.chaldeas.model.Site
import fr.spc.leosoliveres.chaldeas.model.dao.SiteDao

@Database(entities = [Site::class], version=1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
	abstract fun siteDao(): SiteDao

	companion object {
		@Volatile
		private var INSTANCE: AppDatabase? = null

		fun getDatabase(context: Context): AppDatabase? {
			if (INSTANCE == null) {
				synchronized(AppDatabase::class.java) {
					if (INSTANCE == null) {
						INSTANCE = Room.databaseBuilder(
							context.applicationContext,
							AppDatabase::class.java, "app_database"
						)
							.build()
					}
				}
			}
			return INSTANCE
		}
	}
}