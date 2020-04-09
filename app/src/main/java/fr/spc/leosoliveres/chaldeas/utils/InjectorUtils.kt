package fr.spc.leosoliveres.chaldeas.utils

import android.content.Context
import fr.spc.leosoliveres.chaldeas.data.AppRepository
import fr.spc.leosoliveres.chaldeas.data.database.AppDatabase
import fr.spc.leosoliveres.chaldeas.viewmodel.ReportEditViewModelFactory

object InjectorUtils {
	fun provideReportEditViewModelFactory(context:Context):ReportEditViewModelFactory {
		val appRepo = AppRepository.getInstance(AppDatabase.getInstance(context).measureDao())
		return ReportEditViewModelFactory(appRepo)
	}
}