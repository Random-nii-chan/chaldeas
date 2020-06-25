package fr.spc.leosoliveres.chaldeas.model.repository

import androidx.lifecycle.LiveData
import fr.spc.leosoliveres.chaldeas.model.Site
import fr.spc.leosoliveres.chaldeas.model.dao.RecordDao
import fr.spc.leosoliveres.chaldeas.model.dao.ReportDao
import fr.spc.leosoliveres.chaldeas.model.dao.SiteDao
import fr.spc.leosoliveres.chaldeas.model.relations.ReportWithRecords

//repository : paradigme de programmation qui permet de récupérer des données depuis plusieurs sources
//TODO utilisation d'une connexion à la base de données distante du SPC
class AppRepo(
	private val siteDao: SiteDao,
	private val reportDao: ReportDao,
	private val recordDao: RecordDao
) {
	//livedata utilisé par les viewmodels pour observation des données
	val allSites: LiveData<List<Site>> = siteDao.getAll()

	//fonctions appelant des fonctions du DAO
	fun getReportsForSite(id:Long):LiveData<List<ReportWithRecords>> =
		reportDao.getReportsWithRecord(id)

	fun insertReport(rwr:ReportWithRecords) =
		recordDao.insertReportWithRecords(rwr.report,rwr.records)
}