package fr.spc.leosoliveres.chaldeas.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import fr.spc.leosoliveres.chaldeas.model.Family
import fr.spc.leosoliveres.chaldeas.model.Measure
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ReportEditViewModelTest {

	@get:Rule
	val rule = InstantTaskExecutorRule()

	@Test
	fun testAddNewMeasure() {
		//creating the viewmodel
		val vmFactory = ReportEditViewModelFactory()
		val vm = vmFactory.create(ReportEditViewModel::class.java)
		//copy the values
		val initialSize = vm.currentFamily.value!!.size()
		//adding in the measures
		vm.addMeasure(Measure("test","test","t"))
		val newSize = vm.currentFamily.value!!.size()
		//check if
		assertThat(newSize).isEqualTo(initialSize+1)
	}

	@Test
	fun deleteMeasure() {
		//creating the viewmodel
		val vmFactory = ReportEditViewModelFactory()
		val vm = vmFactory.create(ReportEditViewModel::class.java)
		//copy the values
		val initialSize = vm.currentFamily.value!!.size()
		//deleting the measures
		vm.deleteMeasure(vm.currentFamily.value!!.measures[0])
		val newSize = vm.currentFamily.value!!.size()
		//check
		assertThat(newSize).isEqualTo(initialSize-1)
	}

	@Test
	fun testPosition() {
		//creating the viewmodel
		val vmFactory = ReportEditViewModelFactory()
		val vm = vmFactory.create(ReportEditViewModel::class.java)
		val m = vm.currentFamily.value!!.measures[0]
		assertThat(vm.currentFamily.value!!.getIndex(m)).isEqualTo(0)
	}

	@Test
	fun testEditMeasure() {
		//creating the viewmodel
		val vmFactory = ReportEditViewModelFactory()
		val vm = vmFactory.create(ReportEditViewModel::class.java)
		//editing
		val oldMeasure = vm.currentFamily.value!!.measures[0]
		val newMeasure = Measure("YES","YES","YES")
		vm.editMeasure(oldMeasure,newMeasure)
		assertThat(vm.currentFamily.value!!.measures[0]).isEqualTo(newMeasure)
	}

	@Test
	fun testRenameFamily() {
		//creating the viewmodel
		val vmFactory = ReportEditViewModelFactory()
		val vm = vmFactory.create(ReportEditViewModel::class.java)
		vm.renameFamily("test")
		assertThat(vm.currentFamily.value!!.name).isEqualTo("test")
	}

	@Test
	fun testChangeFamily() {
		//creating the viewmodel
		val vmFactory = ReportEditViewModelFactory()
		val vm = vmFactory.create(ReportEditViewModel::class.java)
		val newFamily = vm.familyList.value!![1]
		vm.changeFamily(1)
		assertThat(vm.currentFamily.value!!.name).isEqualTo(newFamily.name)
	}

	@Test
	fun testImpossibleFamilyChange() {
		//creating the viewmodel
		val vmFactory = ReportEditViewModelFactory()
		val vm = vmFactory.create(ReportEditViewModel::class.java)
		val newFamily = vm.familyList.value!![vm.familyList.value!!.size-1]
		vm.changeFamily(9001)
		assertThat(vm.currentFamily.value!!.name).isEqualTo(newFamily.name)
	}
}