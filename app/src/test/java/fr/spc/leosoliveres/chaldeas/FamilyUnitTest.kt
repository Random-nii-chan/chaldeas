package fr.spc.leosoliveres.chaldeas

import fr.spc.leosoliveres.chaldeas.model.Family
import fr.spc.leosoliveres.chaldeas.model.Measure
import org.junit.Test

import org.junit.Assert.*
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
