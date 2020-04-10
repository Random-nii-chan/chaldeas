package fr.spc.leosoliveres.chaldeas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.spc.leosoliveres.chaldeas.model.Measure
import java.lang.IllegalArgumentException

class ReportEditViewModelFactory(): ViewModelProvider.Factory{

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(ReportEditViewModel::class.java)) {
			return ReportEditViewModel() as T
		}
		throw IllegalArgumentException("Unknown viewmodel class")
	}

}