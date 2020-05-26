package fr.spc.leosoliveres.chaldeas.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ReportEditViewModelFactory(ctx: Context) : ViewModelProvider.Factory{

	private val context = ctx

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(ReportEditViewModel::class.java)) {
			return ReportEditViewModel(context) as T
		}
		throw IllegalArgumentException("Unknown viewmodel class")
	}

}