package fr.spc.leosoliveres.chaldeas.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Family(
	@PrimaryKey(autoGenerate = true) val uid:Int=0,
	@ColumnInfo(name="Name") val name:String,
	@Ignore val measures:ArrayList<Measure>
){
	constructor(_name:String):this(0,_name,ArrayList<Measure>())

	constructor(_name:String,_data:ArrayList<Measure>):this(0,_name,_data)

	fun count():Int{
		return measures.size
	}

	override fun toString():String {
		return name
	}
}