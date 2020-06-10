package fr.spc.leosoliveres.chaldeas.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.spc.leosoliveres.chaldeas.model.Site
import fr.spc.leosoliveres.chaldeas.model.database.AppDatabase
import fr.spc.leosoliveres.chaldeas.model.repository.AppRepo

class SiteListViewModel(application: Application) : ViewModel() {
	private val repo:AppRepo
	var sites: LiveData<List<Site>>
	get() = if(field.value == null) field else MutableLiveData()

	init {
		val siteDao = AppDatabase.getDatabase(application)!!.siteDao()
		repo = AppRepo(siteDao)
		sites = repo.allSite
	}
}