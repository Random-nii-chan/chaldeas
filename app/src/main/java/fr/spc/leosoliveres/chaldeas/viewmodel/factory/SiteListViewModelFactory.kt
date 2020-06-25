package fr.spc.leosoliveres.chaldeas.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.spc.leosoliveres.chaldeas.viewmodel.SiteListViewModel
import java.lang.IllegalArgumentException

class SiteListViewModelFactory(val app: Application) : ViewModelProvider.Factory{

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(SiteListViewModel::class.java)) {
			return SiteListViewModel(app) as T
		}
		throw IllegalArgumentException("Unknown viewmodel class")
	}

}