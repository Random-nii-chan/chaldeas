package fr.spc.leosoliveres.nightly.model

import androidx.databinding.BaseObservable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData

//taken from https://medium.com/@tylerwalker/property-aware-mutablelivedata-with-kotlin-4fb5830ae730
class PropertyAwareMutableLiveData<T: BaseObservable>: MutableLiveData<T>() {
	private val callback = object: Observable.OnPropertyChangedCallback() {
		override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
			value = value
		}
	}
	override fun setValue(value: T?) {
		super.setValue(value)
		value?.addOnPropertyChangedCallback(callback)
	}
}