package fr.spc.leosoliveres.chaldeas.adapter

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
		val longitude = sites[position].longitude.toString()
		val latitude = sites[position].latitude.toString()

		holder.nomSite.text = sites[position].nom
		holder.typeSite.text = sites[position].type
		holder.longitude.text = String.format("Longitude : $longitude")
		holder.latitude.text = String.format("Latitude : $latitude")
	}

	override fun getItemCount(): Int = sites.size

	class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
		val nomSite: TextView = itemView.findViewById(R.id.nomSite)
		val typeSite: TextView = itemView.findViewById(R.id.typeSite)
		val latitude: TextView = itemView.findViewById(R.id.latitude)
		val longitude: TextView = itemView.findViewById(R.id.longitude)
	}

}