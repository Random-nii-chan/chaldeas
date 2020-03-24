package fr.spc.leosoliveres.chaldeas.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.spc.leosoliveres.chaldeas.R
import fr.spc.leosoliveres.chaldeas.model.Site

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
	val nomSite: TextView = itemView.findViewById(R.id.nomSite)
	val typeSite: TextView = itemView.findViewById(R.id.typeSite)
	val latitude: TextView = itemView.findViewById(R.id.latitude)
	val longitude: TextView = itemView.findViewById(R.id.longitude)

	fun bind(site: Site, clickListener:OnItemClickListener){
		val longitude = site.longitude.toString()
		val latitude = site.latitude.toString()

		this.nomSite.text = site.nom
		this.typeSite.text = site.type
		this.longitude.text = String.format("Lon. : $longitude")
		this.latitude.text = String.format("Lat. : $latitude")

		itemView.setOnClickListener{
			clickListener.onItemClicked(site)
		}
	}
}