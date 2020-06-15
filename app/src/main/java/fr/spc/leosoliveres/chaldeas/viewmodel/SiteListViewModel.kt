package fr.spc.leosoliveres.chaldeas.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.spc.leosoliveres.chaldeas.model.Site
import fr.spc.leosoliveres.chaldeas.model.dao.RecordDao
import fr.spc.leosoliveres.chaldeas.model.dao.ReportDao
import fr.spc.leosoliveres.chaldeas.model.dao.SiteDao
import fr.spc.leosoliveres.chaldeas.model.database.AppDatabase
import fr.spc.leosoliveres.chaldeas.model.repository.AppRepo

class SiteListViewModel(app: Application) : ViewModel() {
	private val siteDao: SiteDao = AppDatabase.getDatabase(app)!!.siteDao()
	private val reportDao: ReportDao = AppDatabase.getDatabase(app)!!.reportDao()
	private val recordDao: RecordDao = AppDatabase.getDatabase(app)!!.recordDao()
	private val repo = AppRepo(siteDao, reportDao, recordDao)

	var sites: LiveData<List<Site>>
	get() = if(field.value == null) field else MutableLiveData()

	init {
		sites = repo.allSite
	}
}