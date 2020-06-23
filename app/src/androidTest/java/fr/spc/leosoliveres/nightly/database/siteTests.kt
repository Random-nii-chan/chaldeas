package fr.spc.leosoliveres.nightly.database

import android.content.Context
import com.google.common.truth.Truth.assertThat
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import fr.spc.leosoliveres.nightly.model.Site
import fr.spc.leosoliveres.nightly.model.dao.SiteDao
import fr.spc.leosoliveres.nightly.model.database.AppDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SimpleEntityReadWriteTest {
	private lateinit var siteDao: SiteDao
	private lateinit var db: AppDatabase

	@Before
	fun createDb() {
		val context = ApplicationProvider.getApplicationContext<Context>()
		db = AppDatabase.getDatabase(context)!!
		siteDao = db.siteDao()
	}

	@After
	@Throws(IOException::class)
	fun closeDb() {
		db.close()
	}

	@Test
	fun testInsertion() {
		val s = Site("sans nom", 0f, 0f)
		assertThat(siteDao.insertSite(s))
	}
}
