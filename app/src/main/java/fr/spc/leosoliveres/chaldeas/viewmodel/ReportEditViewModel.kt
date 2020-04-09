package fr.spc.leosoliveres.chaldeas.viewmodel

import androidx.lifecycle.ViewModel
import fr.spc.leosoliveres.chaldeas.data.AppRepository
import fr.spc.leosoliveres.chaldeas.data.dao.MeasureDao
import fr.spc.leosoliveres.chaldeas.model.Measure

class ReportEditViewModel(private val appRepo:AppRepository) : ViewModel() {

	fun getMeasures() = appRepo.getMeasures()

	suspend fun addMeasure(measure : Measure) = appRepo.addMeasure(measure)

}