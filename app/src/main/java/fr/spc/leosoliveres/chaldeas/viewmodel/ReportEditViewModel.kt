package fr.spc.leosoliveres.chaldeas.viewmodel

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import fr.spc.leosoliveres.chaldeas.model.DefaultData
import fr.spc.leosoliveres.chaldeas.model.Family
import fr.spc.leosoliveres.chaldeas.model.Measure
import fr.spc.leosoliveres.chaldeas.model.PropertyAwareMutableLiveData
import org.json.JSONArray
import org.json.JSONObject
import java.io.*

class ReportEditViewModel(ctx: Context) : ViewModel() {

	companion object {
		const val PREFS_FILENAME = "prefs.json"
	}

	val familyList = MutableLiveData<ArrayList<Family>>()

	val currentFamily = PropertyAwareMutableLiveData<Family>()

	private var currentFamilyIndex : Int = 0

	private val gson = GsonBuilder().setPrettyPrinting().create()

	init {
		this.familyList.value = initFamilies(ctx)
		this.currentFamily.value = this.familyList.value!![currentFamilyIndex]
	}

	fun getFamilyIndex():Int = currentFamilyIndex

	fun changeFamily(i:Int) {
		val maxValue = this.familyList.value!!.size -1
		currentFamilyIndex = if(i in 0..maxValue) i else maxValue
		this.currentFamily.value = this.familyList.value!![currentFamilyIndex]
	}

	fun familiesToString():ArrayList<String> {
		val al = ArrayList<String>()
		for(i in 0 until this.familyList.value!!.size) al.add(this.familyList.value!![i].toString())
		return al
	}

	fun saveJson(ctx:Context) {
		val jsonString:String
		jsonString = if(this.familyList.value!!.size == 0) {
			JSONArray().toString()
		} else {
			val array = JSONArray()
			for(f in this.familyList.value!!) {
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
		val tempList = this.currentFamily.value!!.measures
		val index = tempList.indexOf(m)
		tempList[index] = newData
		//besoin d'assigner une valeur pour déclencher l'évènement d'observations
		this.currentFamily.value!!.measures = tempList
	}

	fun deleteMeasure(m:Measure) {
		val tempList = this.currentFamily.value!!.measures
		val index = this.currentFamily.value!!.getIndex(m)
		tempList.removeAt(index)
		this.currentFamily.value!!.measures = tempList
	}

	fun duplicateMeasure(m:Measure) {
		addMeasure(m)
	}

	fun addMeasure(m:Measure) {
		val tempList = this.currentFamily.value!!.measures
		tempList.add(m)
		this.currentFamily.value!!.measures = tempList
	}

	//Méthodes CRUD Familles
	fun renameFamily(n:String) {
		this.currentFamily.value!!.name = n
	}

	fun addFamily(f:Family) {
		val tempList = this.familyList.value
		tempList!!.add(f)
		this.familyList.value = tempList
		currentFamilyIndex = this.familyList.value!!.size-1
	}

	fun deleteFamily(f:Family) {
		val tempList = this.familyList.value
		tempList!!.remove(f)
		this.familyList.value = tempList
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
			al = ArrayList(DefaultData.defaultFamilies())
		}
		return al
	}

	private fun initMeasures(count:Int=5):ArrayList<Measure>{
		val arrayList = ArrayList<Measure>()
		for(i in 0..count) arrayList.add(Measure("Mesure n°$i", "Unité $i", "U$i"))
		return arrayList
	}
}