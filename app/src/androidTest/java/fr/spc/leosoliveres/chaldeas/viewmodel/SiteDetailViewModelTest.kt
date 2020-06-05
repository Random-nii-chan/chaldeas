package fr.spc.leosoliveres.chaldeas.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import fr.spc.leosoliveres.chaldeas.model.Family
import fr.spc.leosoliveres.chaldeas.model.Site
import fr.spc.leosoliveres.chaldeas.viewmodel.factory.SiteDetailViewModelFactory
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SiteDetailViewModelTest {
	private lateinit var vm:SiteDetailViewModel

	@get:Rule
	val rule = InstantTaskExecutorRule()

	@Before
	fun setupViewModel() {
		val context = InstrumentationRegistry.getInstrumentation().context
		val site = Site("Site de test", 0.49494f, 0.57848f)
		val vmFactory = SiteDetailViewModelFactory(context,site)
		val vm = vmFactory.create(SiteDetailViewModel::class.java)
	}

	@Test
	fun testInit() {
		assertTrue(vm.families != ArrayList<Family>())
	}
}