package fr.spc.leosoliveres.chaldeas.view.adapter

import fr.spc.leosoliveres.chaldeas.model.Site

//permet d'appliquer un clicklistener sur un élément d'une recyclerview
interface OnItemClickListener{
	//utilisé lorsqu'on clique sur un élément de la liste des sites
	fun onItemClicked(site: Site)
}