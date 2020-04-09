package fr.spc.leosoliveres.chaldeas.data

import androidx.annotation.WorkerThread
import fr.spc.leosoliveres.chaldeas.data.dao.MeasureDao
import fr.spc.leosoliveres.chaldeas.model.Measure

//TODO : ajouter de nouveaux DAO
class AppRepository(private val measureDao:MeasureDao) {

	suspend fun addMeasure(measure : Measure){
		measureDao.insert(measure)
	}

	fun getMeasures() = measureDao.getAll()

	companion object {
		@Volatile private var instance:AppRepository?=null

		//TODO: ajouter les nouveaux DAO
		fun getInstance(measureDao: MeasureDao) = instance?: synchronized(this) {
			instance?:AppRepository(measureDao).also{instance = it}
		}
	}
}