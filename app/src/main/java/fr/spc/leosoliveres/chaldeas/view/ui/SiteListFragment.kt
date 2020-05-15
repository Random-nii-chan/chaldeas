package fr.spc.leosoliveres.chaldeas.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import fr.spc.leosoliveres.chaldeas.R
import fr.spc.leosoliveres.chaldeas.view.adapter.OnItemClickListener
import fr.spc.leosoliveres.chaldeas.view.adapter.SitesAdapter
import fr.spc.leosoliveres.chaldeas.model.entity.Site
import kotlinx.android.synthetic.main.fragment_site_list.*

class SiteListFragment : Fragment(R.layout.fragment_site_list),OnItemClickListener {

	companion object {
		fun newInstance() = SiteListFragment()
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_site_list, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		siteRecyclerView.layoutManager = LinearLayoutManager(activity)
		siteRecyclerView.adapter = SitesAdapter(initVariables(20),this)
	}

	override fun onItemClicked(site: Site){
		findNavController().navigate(R.id.action_siteListFragment_siteDetailFragment,bundleOf("site" to site))
	}

	private fun initVariables(count:Int):ArrayList<Site>{
		val sites : ArrayList<Site> = ArrayList()

		sites.add(
			Site(
				"Nom de relais qui est bien trop long pour rentrer sur une ligne",
				-5.45457856f,
				3.89164832f
			)
		)

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
