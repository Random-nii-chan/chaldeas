package fr.spc.leosoliveres.chaldeas.model.repository

import androidx.lifecycle.LiveData
import fr.spc.leosoliveres.chaldeas.model.Site
import fr.spc.leosoliveres.chaldeas.model.dao.SiteDao

class AppRepo(
	private val siteDao: SiteDao
) {
	val allSite: LiveData<List<Site>> = siteDao.getAll()
}