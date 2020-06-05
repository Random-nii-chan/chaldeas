package fr.spc.leosoliveres.chaldeas.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import fr.spc.leosoliveres.chaldeas.model.Family
import fr.spc.leosoliveres.chaldeas.model.Site
import org.json.JSONArray
import java.io.File

class SiteDetailViewModel(ctx: Context, var site:Site): ViewModel() {
	private val gson = GsonBuilder().setPrettyPrinting().create()

	var families = ArrayList<Family>()

	init {
		families = gson.fromJson(
			getPrefsJSON(ctx),
			object: TypeToken<ArrayList<Family>>(){}.type
		)
	}

	private fun getPrefsJSON(ctx:Context):String {
		val file = File(ctx.filesDir, ReportEditViewModel.PREFS_FILENAME)
		val content = JSONArray(file.readText())
		return content.toString()
	}
}