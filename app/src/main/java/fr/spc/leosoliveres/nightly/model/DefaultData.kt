package fr.spc.leosoliveres.nightly.model

import java.util.*

object DefaultData {
	fun defaultFamilies():List<Family> {
		val defaults = ArrayList<Family>()

		val faisceau = ArrayList<Measure>()
		faisceau.add(Measure("Distant"))
		faisceau.add(Measure("Entrant/Sortant"))
		faisceau.add(Measure("Type Antenne"))
		faisceau.add(Measure("Hauteur","mètres","m"))
		faisceau.add(Measure("Azimut","degrés","°"))
		faisceau.add(Measure("RL Ant.","décibels","db"))
		faisceau.add(Measure("ROS Ant."))
		faisceau.add(Measure("Type coaxial"))
		faisceau.add(Measure("Longueur câble"))
		faisceau.add(Measure("Pertes câble"))
		faisceau.add(Measure("Type rack"))
		faisceau.add(Measure("Numéro série FH"))
		faisceau.add(Measure("Canal"))
		faisceau.add(Measure("Fréquence transmission","MégaHertz","MHz"))
		faisceau.add(Measure("Fréquence réception","MégaHertz","MHz"))
		faisceau.add(Measure("PI","Watts","W"))
		faisceau.add(Measure("PR","Watts","W"))
		faisceau.add(Measure("ROS E/R"))
		faisceau.add(Measure("CAG Loc.","Volts","V"))
		faisceau.add(Measure("CAG Dist.","Volts","V"))
		faisceau.add(Measure("Niveau réception distant"))
		faisceau.add(Measure("Pw HF sortie FH"))
		faisceau.add(Measure("Offset","Kilohertz","kHz"))

		val radioMeasures = ArrayList<Measure>()
		radioMeasures.add(Measure("Type antenne"))
		radioMeasures.add(Measure("Hauteur"))
		radioMeasures.add(Measure("Azimut"))
		radioMeasures.add(Measure("RL Antenne"))
		radioMeasures.add(Measure("ROS Antenne"))
		radioMeasures.add(Measure("Type coaxial"))
		radioMeasures.add(Measure("Longueur de câble"))
		radioMeasures.add(Measure("Pertes câble"))
		radioMeasures.add(Measure("Type de rack"))
		radioMeasures.add(Measure("N° série"))
		radioMeasures.add(Measure("Canal"))
		radioMeasures.add(Measure("Écart fréquence"))
		radioMeasures.add(Measure("Pw HF 50ohm"))
		radioMeasures.add(Measure("DeltaF maximum"))
		radioMeasures.add(Measure("DeltaF nominal"))
		radioMeasures.add(Measure("DeltaF BIS1200"))
		radioMeasures.add(Measure("Sinad"))
		radioMeasures.add(Measure("Distortion"))
		radioMeasures.add(Measure("Squelch"))
		radioMeasures.add(Measure("Hystérésis SQL"))
		radioMeasures.add(Measure("PI"))
		radioMeasures.add(Measure("PR"))
		radioMeasures.add(Measure("ROS E/R"))
		radioMeasures.add(Measure("Étalonnage RSSI"))

		val batteriesMeasures = ArrayList<Measure>()
		batteriesMeasures.add(Measure("Date installation"))
		batteriesMeasures.add(Measure("N° série"))
		batteriesMeasures.add(Measure("Marque"))
		batteriesMeasures.add(Measure("Quantité et type"))
		batteriesMeasures.add(Measure("U en charge"))
		batteriesMeasures.add(Measure("U en secours"))
		batteriesMeasures.add(Measure("R interne"))

		val electriqueMeasures = ArrayList<Measure>()
		electriqueMeasures.add(Measure("Mesure Terre"))
		electriqueMeasures.add(Measure("Index EDF"))
		electriqueMeasures.add(Measure("Test parafoudre"))
		electriqueMeasures.add(Measure("Test 30mA"))

		val f1 = Family("Faisceau 1")
		f1.measures = faisceau
		val f2 = Family("Faisceau 2")
		f2.measures = faisceau
		val radio = Family("Radio")
		radio.measures = radioMeasures
		val batteries = Family("Batteries")
		batteries.measures = batteriesMeasures
		val electrique = Family("Valeurs électriques")
		electrique.measures = electriqueMeasures

		defaults.add(f1)
		defaults.add(f2)
		defaults.add(radio)
		defaults.add(batteries)
		defaults.add(electrique)

		return defaults
	}
}