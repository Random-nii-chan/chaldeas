package fr.spc.leosoliveres.chaldeas.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import fr.spc.leosoliveres.chaldeas.model.Record
import fr.spc.leosoliveres.chaldeas.model.Report
import fr.spc.leosoliveres.chaldeas.model.Site
import fr.spc.leosoliveres.chaldeas.model.dao.RecordDao
import fr.spc.leosoliveres.chaldeas.model.dao.ReportDao
import fr.spc.leosoliveres.chaldeas.model.dao.SiteDao

@TypeConverters(Converters::class)
//passer exportSchema à true pour exportation
//l'exportation se trouve dans [racine projet]/schemas/1.json quand activée
@Database(entities = [Site::class, Report::class, Record::class], version=1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
	abstract fun siteDao(): SiteDao
	abstract fun reportDao(): ReportDao
	abstract fun recordDao(): RecordDao

	//utilisation du pattern singleton
	companion object {
		@Volatile
		//stockage d'une instance
		private var INSTANCE: AppDatabase? = null
		//retourne l'instance si != null, en crée une et la retourne sinon
		fun getDatabase(context: Context): AppDatabase? {
			return INSTANCE ?: synchronized(this) {
				val instance = Room.databaseBuilder(
					context.applicationContext,
					AppDatabase::class.java,
					"app_database.sqlite"
				)
					.addCallback(AppDatabaseCallback(context))
					.build()
				INSTANCE = instance
				// return instance
				instance
			}
		}

	}

	private class AppDatabaseCallback(val context: Context) : RoomDatabase.Callback() {
		//fonction appellée lors de la création de la base de données
		override fun onCreate(db: SupportSQLiteDatabase) {
			super.onCreate(db)
			//utilisation d'un executor pour déléguer la création à un thread en background
			//si pas en background, bloque trop longtemps le thread principal et
			//fait des erreurs de compilation
			ioThread() {
				val siteDao = getDatabase(context)!!.siteDao()
				val count = 20
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