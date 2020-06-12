package fr.spc.leosoliveres.chaldeas.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import fr.spc.leosoliveres.chaldeas.model.Family
import fr.spc.leosoliveres.chaldeas.model.Site
import fr.spc.leosoliveres.chaldeas.model.dao.RecordDao
import fr.spc.leosoliveres.chaldeas.model.dao.ReportDao
import fr.spc.leosoliveres.chaldeas.model.dao.SiteDao
import fr.spc.leosoliveres.chaldeas.model.database.AppDatabase
import fr.spc.leosoliveres.chaldeas.model.relations.ReportWithRecords
import fr.spc.leosoliveres.chaldeas.model.repository.AppRepo
import org.json.JSONArray
import java.io.File
import java.io.FileOutputStream

class SiteDetailViewModel(app: Application, var site:Site): ViewModel() {
	private val siteDao: SiteDao = AppDatabase.getDatabase(app)!!.siteDao()
	private val reportDao: ReportDao = AppDatabase.getDatabase(app)!!.ReportDao()
	private val recordDao: RecordDao = AppDatabase.getDatabase(app)!!.RecordDao()
	private val repo = AppRepo(siteDao, reportDao, recordDao)

	private val gson = GsonBuilder().setPrettyPrinting().create()

	var formTemplate = ArrayList<Family>()
	var previousReports: LiveData<List<ReportWithRecords>>

	init {
		formTemplate = gson.fromJson(getPrefsJSON(app),object: TypeToken<ArrayList<Family>>(){}.type)
		previousReports = repo.getReportsForSite(site.id)
	}

	fun getPreviousReportsSummaries():ArrayList<String> {
		val al = ArrayList<String>()
		if(previousReports.value != null) {
			for (i in previousReports.value!!) {
				al.add(i.report.summary())
			}
		}
		return al
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