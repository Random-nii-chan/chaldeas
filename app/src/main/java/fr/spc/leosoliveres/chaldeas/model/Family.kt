package fr.spc.leosoliveres.chaldeas.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Family(
	@PrimaryKey(autoGenerate = true) val uid:Int=0,
	@ColumnInfo(name="Name") val _name:String,
	@Ignore var _measures:ArrayList<Measure>
) : BaseObservable() {

	@Bindable
	var measures : ArrayList<Measure> = _measures
	set(value) {
		field=value
		notifyPropertyChanged(BR.measures)
	}

	@Bindable
	var name : String = _name
	set(value) {
		field = value
		notifyPropertyChanged(BR.name)
	}

	constructor(_name:String):this(0,_name,ArrayList<Measure>())

	constructor(_name:String,_data:ArrayList<Measure>):this(0,_name,_data)

	fun removeMeasure(i:Int) {
		val tempList = measures
		tempList.removeAt(i)
		measures = tempList
	}

	fun getIndex(m:Measure):Int {
		return if(measures.contains(m))
			measures.indexOf(m)
		else
			-1
	}

	fun size():Int {
		return measures.size
	}

	override fun toString():String {
		return name
	}
}