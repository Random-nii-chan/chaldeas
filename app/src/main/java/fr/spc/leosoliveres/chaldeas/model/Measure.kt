package fr.spc.leosoliveres.chaldeas.model

//mesure = champ de saisie, plus petit élément manipulable dans l'édition du modèle du rapport
//jamais utilisé dans la base de données : ne sert qu'à l'édition du rapport
open class Measure(
	var name:String ="Nom non défini",
	var unitFull:String ="Unité non définie",
	var unitAbriged:String ="Indéf."
){
	//valeur par défaut
	//lors de la création (avec une boîte de dialogue), si aucun nom est passé, une chaîne vide
	//est passée en argument
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