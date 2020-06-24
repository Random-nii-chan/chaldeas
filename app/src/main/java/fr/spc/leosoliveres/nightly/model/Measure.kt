package fr.spc.leosoliveres.nightly.model

open class Measure(
	var name:String ="Nom non défini",
	var unitFull:String ="Unité non définie",
	var unitAbriged:String ="Indéf."
){

	init {
		if(name.isEmpty() || name.isBlank()) name = "Nom non défini"
	}

	constructor(_name:String) : this(_name,"","")

	fun allUnits():String{
		return "$unitFull - $unitAbriged"
	}

	fun nameAndAbriged():String {
		return "$name ($unitAbriged)"
	}

	override fun toString():String {
		return "$name - $unitFull - $unitAbriged"
	}
}