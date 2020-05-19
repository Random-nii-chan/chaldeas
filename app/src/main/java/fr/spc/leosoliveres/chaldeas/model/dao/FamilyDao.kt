package fr.spc.leosoliveres.chaldeas.model.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import fr.spc.leosoliveres.chaldeas.model.entity.Family

@Dao
interface FamilyDao {
	//Create
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertOne(f: Family)

	//Read
	@Query("SELECT * FROM families")
	fun getFamilies():LiveData<List<Family>>

	//Update
	@Update
	suspend fun update(f: Family)

	//Delete
	@Query("DELETE FROM families")
	suspend fun deleteAll()

	@Query("DELETE FROM families WHERE familyId=:id")
	suspend fun deleteOne(id:Long)
}