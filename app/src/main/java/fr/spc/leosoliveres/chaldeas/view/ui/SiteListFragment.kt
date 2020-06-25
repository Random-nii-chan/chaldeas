package fr.spc.leosoliveres.chaldeas.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import fr.spc.leosoliveres.chaldeas.R
import fr.spc.leosoliveres.chaldeas.view.adapter.OnItemClickListener
import fr.spc.leosoliveres.chaldeas.view.adapter.SitesAdapter
import fr.spc.leosoliveres.chaldeas.model.Site
import fr.spc.leosoliveres.chaldeas.viewmodel.SiteListViewModel
import fr.spc.leosoliveres.chaldeas.viewmodel.factory.SiteListViewModelFactory
import kotlinx.android.synthetic.main.fragment_site_list.*

//fragment de la liste des sites
class SiteListFragment : Fragment(R.layout.fragment_site_list),OnItemClickListener {

	private lateinit var viewModelFactory:SiteListViewModelFactory
	private lateinit var viewModel:SiteListViewModel

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
		//Création du viewmodel
		viewModelFactory = SiteListViewModelFactory(requireActivity().application)
		viewModel = viewModelFactory.create(SiteListViewModel::class.java)
		return inflater.inflate(R.layout.fragment_site_list, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		siteRecyclerView.layoutManager = LinearLayoutManager(activity)
		//mettre en place l'adaptateur
		siteRecyclerView.adapter = SitesAdapter(viewModel.sites.value,this)

		viewModel.sites.observe(viewLifecycleOwner, Observer { newList ->
			//changer l'adaptateur en cas de modification de la liste des sites
			siteRecyclerView.swapAdapter(SitesAdapter(ArrayList(newList),this),true)
		})
	}

	//gérer l'appui sur un élément de la liste
	//naviguer au fragment de détail de site
	override fun onItemClicked(site:Site){
		findNavController().navigate(R.id.action_siteListFragment_siteDetailFragment,bundleOf("site" to site))
	}
}
