package fr.spc.leosoliveres.chaldeas.model.dao

import androidx.room.*
import fr.spc.leosoliveres.chaldeas.model.entity.Measure

@Dao
interface MeasureDao {
	//Create
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertOne(m: Measure)

	//Read
	@Query("SELECT * FROM measures")
	fun getAll():List<Measure>

	@Query("SELECT * FROM measures WHERE containingFamilyId = :id")
	fun getFromContainingId(id:Long):List<Measure>

	//Update
	@Update
	fun update(m: Measure)

	//Delete
	@Query("DELETE FROM measures")
	suspend fun deleteAll()
}