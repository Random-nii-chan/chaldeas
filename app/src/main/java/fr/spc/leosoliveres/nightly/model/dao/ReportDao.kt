package fr.spc.leosoliveres.nightly.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.spc.leosoliveres.nightly.model.relations.ReportWithRecords

@Dao
interface ReportDao {
	@Transaction
	@Query("SELECT * FROM Reports WHERE siteId = :siteId")
	fun getReportsWithRecord(siteId:Long):LiveData<List<ReportWithRecords>>
}