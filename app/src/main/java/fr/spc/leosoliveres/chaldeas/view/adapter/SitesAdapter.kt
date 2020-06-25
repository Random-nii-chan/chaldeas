package fr.spc.leosoliveres.chaldeas.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.spc.leosoliveres.chaldeas.R
import fr.spc.leosoliveres.chaldeas.model.Site

//adaptateur de la recyclerview des sites
class SitesAdapter(private val sites: List<Site>?, private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<SitesAdapter.ViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view:View = LayoutInflater.from(parent.context).inflate(R.layout.site_row, parent, false)
		return ViewHolder(view)
	}

	override fun onBindViewHolder(holderSite: ViewHolder, position: Int) {
		val site = sites!![position]
		holderSite.bind(site,itemClickListener)
	}

	override fun getItemCount(): Int = sites?.size ?: 0

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		private val nomSite: TextView = itemView.findViewById(R.id.nomSite)
		private val typeSite: TextView = itemView.findViewById(R.id.typeSite)
		private val latitude: TextView = itemView.findViewById(R.id.latitude)
		private val longitude: TextView = itemView.findViewById(R.id.longitude)

		fun bind(site: Site, clickListener:OnItemClickListener){
			this.nomSite.text = site.name
			this.typeSite.text = site.type
			this.longitude.text = site.lonString()
			this.latitude.text = site.latString()

			itemView.setOnClickListener{
				clickListener.onItemClicked(site)
			}
		}
	}
}