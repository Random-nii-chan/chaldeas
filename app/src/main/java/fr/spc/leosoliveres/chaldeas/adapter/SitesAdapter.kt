package fr.spc.leosoliveres.chaldeas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.spc.leosoliveres.chaldeas.R
import fr.spc.leosoliveres.chaldeas.model.Site

class SitesAdapter(private val sites: ArrayList<Site>, val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<ViewHolderSite>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSite {
		val view:View = LayoutInflater.from(parent.context).inflate(R.layout.site_row, parent, false)
		return ViewHolderSite(view)
	}

	override fun onBindViewHolder(holderSite: ViewHolderSite, position: Int) {
		val site = sites[position]
		holderSite.bind(site,itemClickListener)
	}

	override fun getItemCount(): Int = sites.size
}