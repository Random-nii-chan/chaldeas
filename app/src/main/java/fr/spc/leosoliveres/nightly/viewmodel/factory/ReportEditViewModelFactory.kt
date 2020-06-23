package fr.spc.leosoliveres.nightly.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.spc.leosoliveres.nightly.viewmodel.ReportEditViewModel
import java.lang.IllegalArgumentException

class ReportEditViewModelFactory(private val ctx: Context) : ViewModelProvider.Factory{

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(ReportEditViewModel::class.java)) {
			return ReportEditViewModel(ctx) as T
		}
		throw IllegalArgumentException("Unknown viewmodel class")
	}

}