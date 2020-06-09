package fr.spc.leosoliveres.chaldeas.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fr.spc.leosoliveres.chaldeas.model.Site
import fr.spc.leosoliveres.chaldeas.model.database.AppDatabase
import fr.spc.leosoliveres.chaldeas.model.repository.AppRepo

class SiteListViewModel(application: Application) : ViewModel() {
	private val repo:AppRepo
	val sites: LiveData<List<Site>>

	init {
		val siteDao = AppDatabase.getDatabase(application)!!.siteDao()
		repo = AppRepo(siteDao)
		sites = repo.allSite
	}
}