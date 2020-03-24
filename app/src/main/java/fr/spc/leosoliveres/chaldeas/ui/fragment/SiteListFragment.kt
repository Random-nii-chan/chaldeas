package fr.spc.leosoliveres.chaldeas.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import fr.spc.leosoliveres.chaldeas.R
import fr.spc.leosoliveres.chaldeas.adapter.OnItemClickListener
import fr.spc.leosoliveres.chaldeas.adapter.SitesAdapter
import fr.spc.leosoliveres.chaldeas.model.Site
import kotlinx.android.synthetic.main.site_list_fragment.*

class SiteListFragment : Fragment(R.layout.site_list_fragment),OnItemClickListener {

	companion object {
		fun newInstance() = SiteListFragment()
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.site_list_fragment, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		siteRecyclerView.layoutManager = LinearLayoutManager(activity)
		siteRecyclerView.adapter = SitesAdapter(initVariables(20),this)
	}

	override fun onItemClicked(site:Site){
		Toast.makeText(activity, site.nom, Toast.LENGTH_LONG).show()
	}

	private fun initVariables(count:Int):ArrayList<Site>{
		val sites : ArrayList<Site> = ArrayList()

		sites.add(Site("Nom de relais qui est bien trop long pour rentrer sur une ligne",-5.45457856f,3.89164832f))

		for (i in 0..count){
			sites.add(
				Site(
					"Station $i",
					((-90..89).random() + Math.random()).toFloat(),
					((-180..179).random() + Math.random()).toFloat()
				)
			)
		}

		return sites
	}
}
