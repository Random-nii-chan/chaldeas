package fr.spc.leosoliveres.chaldeas.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import fr.spc.leosoliveres.chaldeas.model.Family
import fr.spc.leosoliveres.chaldeas.model.Site
import org.json.JSONArray
import java.io.File
import java.io.FileOutputStream

class SiteDetailViewModel(ctx: Context, var site:Site): ViewModel() {
	private val gson = GsonBuilder().setPrettyPrinting().create()

	var families = ArrayList<Family>()

	init {
		families = gson.fromJson(getPrefsJSON(ctx),object: TypeToken<ArrayList<Family>>(){}.type)
	}

	private fun getPrefsJSON(ctx:Context):String {
		var file = File(ctx.filesDir, ReportEditViewModel.PREFS_FILENAME)
		if(!file.exists()) {
			val fos: FileOutputStream? = ctx.applicationContext.openFileOutput(
				ReportEditViewModel.PREFS_FILENAME,
				Context.MODE_PRIVATE
			)
			fos?.write(JSONArray().toString().toByteArray())
			Toast.makeText(
				ctx,
				"Modèle de rapport sauvegardé sur ${ctx.filesDir}/${ReportEditViewModel.PREFS_FILENAME}",
				Toast.LENGTH_LONG
			).show()
			fos?.close()
			file = File(ctx.filesDir, ReportEditViewModel.PREFS_FILENAME)
		}
		val content = JSONArray(file.readText())
		return content.toString()
	}
}