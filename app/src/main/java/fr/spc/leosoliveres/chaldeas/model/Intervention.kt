package fr.spc.leosoliveres.chaldeas.model

import java.sql.Timestamp

class Intervention(
	_agent: Agent,
	_timestamp: Timestamp,
	_siteIntervention: Site,
	_commentaire: String = ""
) {
	private var agent:Agent = _agent
	private var timestamp: Timestamp = _timestamp
	private var commentaire:String = _commentaire
	private var siteIntervention:Site = _siteIntervention

}