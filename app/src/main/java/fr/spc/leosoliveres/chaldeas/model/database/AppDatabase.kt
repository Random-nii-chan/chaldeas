package fr.spc.leosoliveres.chaldeas.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import fr.spc.leosoliveres.chaldeas.model.Site
import fr.spc.leosoliveres.chaldeas.model.dao.SiteDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Site::class], version=1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
	abstract fun siteDao(): SiteDao

	companion object {
		@Volatile
		private var INSTANCE: AppDatabase? = null

		fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase? {
			return INSTANCE ?: synchronized(this) {
				val instance = Room.databaseBuilder(
					context.applicationContext,
					AppDatabase::class.java,
					"word_database"
				)
					.addCallback(AppDatabaseCallback(scope))
					.build()
				INSTANCE = instance
				// return instance
				instance
			}
		}

		private class AppDatabaseCallback(private val scope: CoroutineScope) :
			RoomDatabase.Callback() {
			override fun onCreate(db: SupportSQLiteDatabase) {
				super.onCreate(db)
				INSTANCE?.let { database ->
					scope.launch {
						populateDatabase(database.siteDao())
					}
				}
			}

			fun populateDatabase(siteDao: SiteDao, count: Int = 20) {
				// Delete all content here.
				siteDao.deleteAll()
				for (i in 0..count) {
					siteDao.insertSite(
						Site(
							"Station $i",
							((-90..89).random() + Math.random()).toFloat(),
							((-180..179).random() + Math.random()).toFloat()
						)
					)
				}
			}
		}
	}
}