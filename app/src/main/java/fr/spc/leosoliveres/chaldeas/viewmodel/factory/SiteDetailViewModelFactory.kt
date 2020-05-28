package fr.spc.leosoliveres.chaldeas.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.spc.leosoliveres.chaldeas.viewmodel.SiteDetailViewModel
import java.lang.IllegalArgumentException

class SiteDetailViewModelFactory() : ViewModelProvider.Factory {
	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(SiteDetailViewModel::class.java)) {
			return SiteDetailViewModel() as T
		}
		throw IllegalArgumentException("Unknown viewmodel class")
	}
}