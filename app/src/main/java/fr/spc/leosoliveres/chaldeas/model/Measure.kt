package fr.spc.leosoliveres.chaldeas.model

data class Measure(
	var name:String ="Nom non défini",
	var unitFull:String ="Unité non définie",
	var unitAbriged:String ="Indéf."
){

	init {
		if(name.isEmpty() || name.isBlank()) name = "Nom non défini"
		if(unitFull.isEmpty() || unitFull.isBlank()) unitFull = "Unité non définie"
		if(unitAbriged.isEmpty() || unitAbriged.isBlank()) unitAbriged = "Indéf."
	}

	fun allUnits():String{
		return "$unitFull - $unitAbriged"
	}

	override fun toString():String {
		return "$name - $unitFull - $unitAbriged"
	}
}