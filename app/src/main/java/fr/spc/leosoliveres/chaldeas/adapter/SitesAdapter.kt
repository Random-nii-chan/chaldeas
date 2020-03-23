package fr.spc.leosoliveres.chaldeas.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.spc.leosoliveres.chaldeas.R
import fr.spc.leosoliveres.chaldeas.model.Site
import java.util.*

class SitesAdapter(private val sites: ArrayList<Site>) : RecyclerView.Adapter<SitesAdapter.ViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view:View = LayoutInflater.from(parent.context).inflate(R.layout.site_row, parent, false)
		return ViewHolder(view)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val site = sites[position]

		val longitude = site.longitude.toString()
		val latitude = site.latitude.toString()

		holder.nomSite.text = site.nom
		holder.typeSite.text = site.type
		holder.longitude.text = String.format("Lon. : $longitude")
		holder.latitude.text = String.format("Lat. : $latitude")
	}

	override fun getItemCount(): Int = sites.size

	class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
		val nomSite: TextView = itemView.findViewById(R.id.nomSite)
		val typeSite: TextView = itemView.findViewById(R.id.typeSite)
		val latitude: TextView = itemView.findViewById(R.id.latitude)
		val longitude: TextView = itemView.findViewById(R.id.longitude)
	}

}