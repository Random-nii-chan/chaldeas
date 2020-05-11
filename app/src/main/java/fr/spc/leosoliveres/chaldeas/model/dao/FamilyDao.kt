package fr.spc.leosoliveres.chaldeas.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import fr.spc.leosoliveres.chaldeas.model.Family

@Dao
interface FamilyDao {
	//TODO méthode de sauvegarde Famille
	@Insert
	fun insert(f: Family)
	//TODO méthode de chargement Famille
	@Query("SELECT * FROM families")
	fun getFamilies():List<Family>
}