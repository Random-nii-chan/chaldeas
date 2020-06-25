package fr.spc.leosoliveres.chaldeas.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import fr.spc.leosoliveres.chaldeas.model.Record
import fr.spc.leosoliveres.chaldeas.model.Report

@Dao
abstract class RecordDao {
	//utilisé lors de l'insertion d'un nouveau rapport dans la BDD room
	//TODO enregistrement d'un rapport dans Room (DAO)
	@Transaction
	open fun insertReportWithRecords(report: Report, records : List<Record>) {
		val reportId = insertReport(report)
		for(record in records) {
			record.reportId = reportId
			insertRecord(record)
		}
	}

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	abstract fun insertRecord(rec:Record):Long

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	abstract fun insertReport(report: Report):Long
}