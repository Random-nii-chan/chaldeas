package fr.spc.leosoliveres.chaldeas.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.spc.leosoliveres.chaldeas.model.Measure

@Dao
interface MeasureDao {
	//create
	@Insert
	suspend fun insert(m:Measure)
	//read
	@Query("SELECT * FROM measure")
	fun getAll():List<Measure>
	//update
	@Update
	suspend fun update(m:Measure)
	//delete
	@Delete
	fun delete(m:Measure)
}