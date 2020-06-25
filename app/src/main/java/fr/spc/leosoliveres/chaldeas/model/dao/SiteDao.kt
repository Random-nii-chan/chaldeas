package fr.spc.leosoliveres.chaldeas.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.spc.leosoliveres.chaldeas.model.Site

@Dao
interface SiteDao {
	//insertion d'un site
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insertSite(s: Site)

	//insertion d'une liste de sites
	@Insert
	fun insertAll(l:List<Site>)

	//obtenir tous les sites (retourner en livedata pour observation dans repo)
	@Query("SELECT * FROM sites ORDER BY site_id ASC")
	fun getAll(): LiveData<List<Site>>

	//purger table des sites
	@Query("DELETE FROM sites")
	fun deleteAll()
}