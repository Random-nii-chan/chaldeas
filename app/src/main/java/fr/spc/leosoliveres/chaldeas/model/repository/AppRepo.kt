package fr.spc.leosoliveres.chaldeas.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.spc.leosoliveres.chaldeas.model.entity.Family
import fr.spc.leosoliveres.chaldeas.model.dao.FamilyDao

class AppRepo(
	localF: FamilyDao
){
	private val res = localF.getFamilies()
	private val al = ArrayList<Family>()
	val families: LiveData<List<Family>> = if(res.value == null) MutableLiveData(al) else res

	/*
	suspend fun getFilledFamilies() : LiveData<List<Family>> {
		val al = ArrayList<Family>()
		val families = localF.getFamilies()
		var measures:ArrayList<Measure>

		for(i in families.indices) {
			measures = ArrayList(localM.getFromContainingId(families[i].familyId))
			families[i].measures = measures
		}

		return MutableLiveData(al)
	}
	 */
}