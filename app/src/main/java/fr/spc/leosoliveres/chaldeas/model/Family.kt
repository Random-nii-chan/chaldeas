package fr.spc.leosoliveres.chaldeas.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.google.gson.annotations.Expose

//collection de mesures, utilisé pour la génération du rapport et l'enregistrement de son modèle
//les champs sont observables : lors de la modification d'un d'entre eux les observateurs
//de l'objet sont avertis du changement
data class Family(private val _name:String) : BaseObservable() {
	@Bindable
	//utilisé pour montrer les valeurs à prendre en compte lors de la conversion par gson
	//voir reporteditviewmodel
	@Expose
	var name : String = _name
	set(value) {
		field = value
		notifyPropertyChanged(BR.name)
	}

	@Bindable
	@Expose
	var measures : ArrayList<Measure> = ArrayList()
	set(value) {
		field=value
		notifyPropertyChanged(BR.measures)
	}

	fun getIndex(m:Measure):Int {
		return if(measures.contains(m))
			measures.indexOf(m)
		else
			-1
	}

	fun measureCount():Int {
		return measures.size
	}

	override fun toString():String {
		return name
	}
}