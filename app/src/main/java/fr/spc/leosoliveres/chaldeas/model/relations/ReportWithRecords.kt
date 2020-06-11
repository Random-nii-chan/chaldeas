package fr.spc.leosoliveres.chaldeas.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import fr.spc.leosoliveres.chaldeas.model.Record
import fr.spc.leosoliveres.chaldeas.model.Report

data class ReportWithRecords(
	@Embedded val report:Report,
	@Relation(
		parentColumn = "report_id",
		entityColumn = "record_id"
	) val records:List<Record>
)