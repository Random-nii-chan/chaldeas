package fr.spc.leosoliveres.chaldeas.model.dao

import androidx.room.*
import fr.spc.leosoliveres.chaldeas.model.entity.Family

@Dao
interface FamilyDao {
	//Create
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insertOne(f: Family)

	//Read
	@Query("SELECT * FROM families")
	fun getFamilies():List<Family>

	//Update
	@Update
	fun update(f: Family)

	//Delete
	@Query("DELETE FROM families")
	fun deleteAll()
}