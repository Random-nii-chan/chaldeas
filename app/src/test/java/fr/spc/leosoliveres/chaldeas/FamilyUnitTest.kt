package fr.spc.leosoliveres.chaldeas

import fr.spc.leosoliveres.chaldeas.model.entity.Family
import fr.spc.leosoliveres.chaldeas.model.entity.Measure
import org.junit.Test

import org.junit.Before

class FamilyUnitTest {
	@Before
	fun initVariables() {
		val arrayList = ArrayList<Measure>()
	}

	@Test
	fun testInsertMeasure() {
		val f = Family("test")
		assert(f.size() == 0)
	}
}
