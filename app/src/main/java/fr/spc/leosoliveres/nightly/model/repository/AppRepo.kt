package fr.spc.leosoliveres.nightly.model.repository

import androidx.lifecycle.LiveData
import fr.spc.leosoliveres.nightly.model.Site
import fr.spc.leosoliveres.nightly.model.dao.RecordDao
import fr.spc.leosoliveres.nightly.model.dao.ReportDao
import fr.spc.leosoliveres.nightly.model.dao.SiteDao
import fr.spc.leosoliveres.nightly.model.relations.ReportWithRecords

class AppRepo(
	private val siteDao: SiteDao,
	private val reportDao: ReportDao,
	private val recordDao: RecordDao
) {
	val allSites: LiveData<List<Site>> = siteDao.getAll()

	fun getReportsForSite(id:Long):LiveData<List<ReportWithRecords>> =
		reportDao.getReportsWithRecord(id)

	fun insertReport(rwr:ReportWithRecords) =
		recordDao.insertReportWithRecords(rwr.report,rwr.records)
}