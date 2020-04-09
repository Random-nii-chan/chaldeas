package fr.spc.leosoliveres.chaldeas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.spc.leosoliveres.chaldeas.data.AppRepository

class ReportEditViewModelFactory(private val appRepo:AppRepository): ViewModelProvider.NewInstanceFactory(){

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		return ReportEditViewModel(appRepo) as T
	}
}