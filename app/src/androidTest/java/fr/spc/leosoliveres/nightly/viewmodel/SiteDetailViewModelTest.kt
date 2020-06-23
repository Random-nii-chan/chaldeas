package fr.spc.leosoliveres.nightly.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SiteDetailViewModelTest {
	private lateinit var vm:SiteDetailViewModel

	@get:Rule
	val rule = InstantTaskExecutorRule()
}