package fr.spc.leosoliveres.chaldeas.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.spc.leosoliveres.chaldeas.model.Site
import fr.spc.leosoliveres.chaldeas.viewmodel.SiteDetailViewModel
import java.lang.IllegalArgumentException

class SiteDetailViewModelFactory(private val app: Application, private val site:Site) : ViewModelProvider.Factory {

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(SiteDetailViewModel::class.java)) {
			return SiteDetailViewModel(app,site) as T
		}
		throw IllegalArgumentException("Unknown viewmodel class")
	}
}