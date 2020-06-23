package fr.spc.leosoliveres.nightly.view.adapter

import fr.spc.leosoliveres.nightly.model.Site

interface OnItemClickListener{
	fun onItemClicked(site: Site)
}