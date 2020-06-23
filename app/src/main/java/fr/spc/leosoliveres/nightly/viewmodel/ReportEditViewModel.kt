package fr.spc.leosoliveres.nightly.viewmodel

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import fr.spc.leosoliveres.nightly.model.Family
import fr.spc.leosoliveres.nightly.model.Measure
import fr.spc.leosoliveres.nightly.model.PropertyAwareMutableLiveData
import org.json.JSONArray
import org.json.JSONObject
import java.io.*

class ReportEditViewModel(ctx: Context) : ViewModel() {

	companion object {
		const val PREFS_FILENAME = "prefs.json"
	}

	private val _familyList = MutableLiveData<ArrayList<Family>>()
	val familyList : LiveData<ArrayList<Family>>
		get() = _familyList

	private var currentFamilyIndex : Int = 0

	private val _currentFamily = PropertyAwareMutableLiveData<Family>()
	val currentFamily : LiveData<Family>
		get() = _currentFamily

	private val gson = GsonBuilder().setPrettyPrinting().create()

	init {
		_familyList.value = initFamilies(ctx)
		_currentFamily.value = _familyList.value!![currentFamilyIndex]
	}

	fun getFamilyIndex():Int = currentFamilyIndex

	fun changeFamily(i:Int) {
		val maxValue = _familyList.value!!.size -1
		currentFamilyIndex = if(i in 0..maxValue) i else maxValue
		_currentFamily.value = _familyList.value!![currentFamilyIndex]
	}

	fun familiesToString():ArrayList<String> {
		val al = ArrayList<String>()
		for(i in 0 until _familyList.value!!.size) al.add(_familyList.value!![i].toString())
		return al
	}

	fun saveJson(ctx:Context) {
		val jsonString:String
		jsonString = if(_familyList.value!!.size == 0) {
			JSONArray().toString()
		} else {
			val array = JSONArray()
			for(f in _familyList.value!!) {
				array.put(encodeFamily(f))
			}
			array.toString()
		}

		val fos: FileOutputStream? = ctx.applicationContext.openFileOutput(PREFS_FILENAME,MODE_PRIVATE)
		fos?.write(jsonString.toByteArray())
		Toast.makeText(ctx,"Modèle de rapport sauvegardé sur ${ctx.filesDir}/${PREFS_FILENAME}",Toast.LENGTH_LONG).show()
		fos?.close()
	}

	private fun loadJson(ctx: Context):String {
		val file = File(ctx.filesDir, PREFS_FILENAME)
		val content = JSONArray(file.readText())
		return content.toString()
	}

	private fun encodeFamily(f:Family):JSONObject {
		return JSONObject(gson.toJson(f))
	}

	//Méthodes CRUD mesures
	fun editMeasure(m:Measure,newData:Measure) {
		val tempList = _currentFamily.value?.measures
		val index = tempList?.indexOf(m)
		if (index != null) tempList[index] = newData
		//besoin d'assigner une valeur pour déclencher l'évènement d'observations
		_currentFamily.value!!.measures = tempList!!
	}

	fun deleteMeasure(m:Measure) {
		_currentFamily.value!!.removeMeasure(_currentFamily.value!!.getIndex(m))
	}

	fun duplicateMeasure(m:Measure) {
		/*
		val tempList = _currentFamily.value?.measures
		tempList?.add(Measure("Copie de ${m.name}",m.unitFull,m.unitAbriged))
		_currentFamily.value!!.measures = tempList!!
		*/
		 _currentFamily.value!!.addMeasure(Measure("Copie de ${m.name}",m.unitFull,m.unitAbriged))
	}

	fun addMeasure(m:Measure) {
		val tempList = _currentFamily.value!!.measures
		tempList.add(m)
		_currentFamily.value!!.measures = tempList
	}

	//Méthodes CRUD Familles
	fun renameFamily(n:String) {
		_currentFamily.value!!.name = n
	}

	fun addFamily(f:Family) {
		val tempList = _familyList.value
		tempList!!.add(f)
		_familyList.value = tempList
		currentFamilyIndex = _familyList.value!!.size-1
	}

	fun deleteFamily(f:Family) {
		val tempList = _familyList.value
		tempList!!.remove(f)
		_familyList.value = tempList
		currentFamilyIndex--
	}

	//initialisations
	private fun initFamilies(ctx:Context,count:Int=3):ArrayList<Family> {
		val file = File(ctx.filesDir, PREFS_FILENAME)
		var al = ArrayList<Family>()
		if(file.exists()) {
			al = gson.fromJson(loadJson(ctx),object:TypeToken<ArrayList<Family>>(){}.type)
		}
		if (al.size == 0){
			for(i in 0..count) {
				al.add(Family("Famille N°${i}",initMeasures()))
			}
		}
		return al
	}

	private fun initMeasures(count:Int=5):ArrayList<Measure>{
		val arrayList = ArrayList<Measure>()
		for(i in 0..count) arrayList.add(Measure("Mesure n°$i", "Unité $i", "U$i"))
		return arrayList
	}
}