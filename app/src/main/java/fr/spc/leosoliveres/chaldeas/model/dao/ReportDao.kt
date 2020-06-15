package fr.spc.leosoliveres.chaldeas.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import fr.spc.leosoliveres.chaldeas.model.relations.ReportWithRecords

@Dao
interface ReportDao {
	@Transaction
	@Query("SELECT * FROM Reports WHERE siteId = :siteId")
	fun getReportsWithRecord(siteId:Long):LiveData<List<ReportWithRecords>>
}