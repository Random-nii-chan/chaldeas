package fr.spc.leosoliveres.chaldeas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.spc.leosoliveres.chaldeas.R
import fr.spc.leosoliveres.chaldeas.model.Site

class SitesAdapter(private val sites: ArrayList<Site>, val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<ViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view:View = LayoutInflater.from(parent.context).inflate(R.layout.site_row, parent, false)
		return ViewHolder(view)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val site = sites[position]
		holder.bind(site,itemClickListener)
	}

	override fun getItemCount(): Int = sites.size
}