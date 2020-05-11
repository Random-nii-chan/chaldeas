package fr.spc.leosoliveres.chaldeas.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fr.spc.leosoliveres.chaldeas.model.Measure

@Dao
interface MeasureDao {
	//TODO méthode de sauvegarde Mesure
	@Insert
	fun save(m:Measure)

	//TODO méthode de suppression mesure
	@Delete
	fun delete(m: Measure)

	//TODO méthode de chargement Mesure
	@Query("SELECT * FROM measures")
	fun getAll():List<Measure>
}