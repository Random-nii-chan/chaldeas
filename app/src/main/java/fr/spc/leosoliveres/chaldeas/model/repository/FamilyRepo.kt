package fr.spc.leosoliveres.chaldeas.model.repository

import androidx.lifecycle.LiveData
import fr.spc.leosoliveres.chaldeas.model.dao.FamilyDao

class FamilyRepo constructor(private val local:FamilyDao) {
	//TODO obtenir la liste des familles
	//First check if the article exists in the local database
	fun getFamilies()= local.getFamilies()
}