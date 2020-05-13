package fr.spc.leosoliveres.chaldeas.model.dao

import androidx.room.*
import fr.spc.leosoliveres.chaldeas.model.Measure

@Dao
interface MeasureDao {
	//Create
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertOne(m:Measure)

	//Read
	@Query("SELECT * FROM measures")
	fun getAll():List<Measure>

	//Update
	@Update
	fun update(m:Measure)

	//Delete
	@Query("DELETE FROM measures")
	suspend fun deleteAll()
}