package fr.spc.leosoliveres.chaldeas.model

open class Measure(
	val name:String,
	private val unitFull:String,
	private val unitAbriged:String
){
	private fun allUnits():String{
		return "%s (%s)".format(this.unitFull, this.unitAbriged)
	}
}