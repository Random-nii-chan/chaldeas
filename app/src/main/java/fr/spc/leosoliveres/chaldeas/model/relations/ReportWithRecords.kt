package fr.spc.leosoliveres.chaldeas.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import fr.spc.leosoliveres.chaldeas.model.Record
import fr.spc.leosoliveres.chaldeas.model.Report

data class ReportWithRecords(
	@Embedded val report:Report,
	@Relation(parentColumn = "report_id",entityColumn = "record_id")
	val records:List<Record>
) {
	fun getHeaders():List<String> {
		val al = ArrayList<String>()
		for(i in records) {
			if(!al.contains(i.family)) {
				al.add(i.family)
			}
		}
		return al
	}

	fun getMap():List<List<Record>> {
		val globalList = ArrayList<List<Record>>()
		for(i in getHeaders()) {
			val list = ArrayList<Record>()
			for(j in records) if(j.family == i) list.add(j)
			globalList.add(list)
		}
		return globalList
	}
}