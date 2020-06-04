package fr.spc.leosoliveres.chaldeas.model

class Record(
	name:String,
	unitFull:String,
	unitAbriged:String,
	var value:Float
) : Measure(
	name,
	unitFull,
	unitAbriged)