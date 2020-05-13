package fr.spc.leosoliveres.chaldeas.model.dao

import androidx.room.*
import fr.spc.leosoliveres.chaldeas.model.FamilyWithMeasures
import fr.spc.leosoliveres.chaldeas.model.Family

@Dao
interface FamilyDao {
	//Create
	@Insert
	fun insert(f: Family)

	//Read
	@Query("SELECT * FROM families")
	fun getFamilies():List<Family>

	@Transaction
	@Query("SELECT * FROM families")
	fun getFamiliesWithMeasures():List<FamilyWithMeasures>

	//Update
	@Update
	fun update(f:Family)

	//Delete
	@Query("DELETE FROM families")
	fun deleteAll()
}