package fr.spc.leosoliveres.chaldeas.model

class Report(
	//TODO: faire la classe Agent
	val agent:String,
	val date:String,
	val priority:Int,
	val comment:String,
	val families:ArrayList<Family>
) {
}