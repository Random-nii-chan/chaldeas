package fr.spc.leosoliveres.chaldeas.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.spc.leosoliveres.chaldeas.model.relations.ReportWithRecords

@Dao
interface ReportDao {
	//obtenir la liste des rapports avec leurs enregistrements
	@Transaction
	@Query("SELECT * FROM Reports WHERE siteId = :siteId")
	fun getReportsWithRecord(siteId:Long):LiveData<List<ReportWithRecords>>
}