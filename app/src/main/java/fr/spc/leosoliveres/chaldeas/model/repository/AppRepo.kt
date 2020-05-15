package fr.spc.leosoliveres.chaldeas.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.spc.leosoliveres.chaldeas.model.entity.Family
import fr.spc.leosoliveres.chaldeas.model.dao.FamilyDao
import fr.spc.leosoliveres.chaldeas.model.dao.MeasureDao
import fr.spc.leosoliveres.chaldeas.model.entity.Measure

class AppRepo constructor(
	private val localF:FamilyDao,
	private val localM:MeasureDao
){
	val families:MutableLiveData<ArrayList<Family>> = getFilledFamilies()

	private fun getFilledFamilies() : MutableLiveData<ArrayList<Family>> {
		val al = ArrayList<Family>()
		val families = localF.getFamilies()
		var measures:ArrayList<Measure>

		for(i in families.indices) {
			measures = ArrayList(localM.getFromContainingId(families[i].familyId))
			families[i].measures = measures
		}

		return MutableLiveData(al)
	}
}