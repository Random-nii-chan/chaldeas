package fr.spc.leosoliveres.chaldeas.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import fr.spc.leosoliveres.chaldeas.model.Measure
import fr.spc.leosoliveres.chaldeas.viewmodel.factory.ReportEditViewModelFactory
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ReportEditViewModelTest {

	private lateinit var vm:ReportEditViewModel

	@get:Rule
	val rule = InstantTaskExecutorRule()

	@Before
	fun setupViewModel() {
		val vmFactory =
			ReportEditViewModelFactory()
		vm = vmFactory.create(ReportEditViewModel::class.java)
	}


	@Test
	fun testAddNewMeasure() {
		//copy the values
		val initialSize = vm.currentFamily.value!!.measureCount()
		//adding in the measures
		vm.addMeasure(Measure("test","test","t"))
		val newSize = vm.currentFamily.value!!.measureCount()
		//check if
		assertThat(newSize).isEqualTo(initialSize+1)
	}

	@Test
	fun deleteMeasure() {
		//copy the values
		val initialSize = vm.currentFamily.value!!.measureCount()
		//deleting the measures
		vm.deleteMeasure(vm.currentFamily.value!!.measures[0])
		val newSize = vm.currentFamily.value!!.measureCount()
		//check
		assertThat(newSize).isEqualTo(initialSize-1)
	}

	@Test
	fun testPosition() {
		val m = vm.currentFamily.value!!.measures[0]
		assertThat(vm.currentFamily.value!!.getIndex(m)).isEqualTo(0)
	}

	@Test
	fun testEditMeasure() {
		//editing
		val oldMeasure = vm.currentFamily.value!!.measures[0]
		val newMeasure = Measure("YES","YES","YES")
		vm.editMeasure(oldMeasure,newMeasure)
		assertThat(vm.currentFamily.value!!.measures[0]).isEqualTo(newMeasure)
	}

	@Test
	fun testRenameFamily() {
		vm.renameFamily("test")
		assertThat(vm.currentFamily.value!!.name).isEqualTo("test")
	}

	@Test
	fun testChangeFamily() {
		val newFamily = vm.familyList.value!![1]
		vm.changeFamily(1)
		assertThat(vm.currentFamily.value!!.name).isEqualTo(newFamily.name)
	}

	@Test
	fun testImpossibleFamilyChange() {
		val newFamily = vm.familyList.value!![vm.familyList.value!!.size-1]
		vm.changeFamily(9001)
		assertThat(vm.currentFamily.value!!.name).isEqualTo(newFamily.name)
	}
}