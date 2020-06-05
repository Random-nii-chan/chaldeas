package fr.spc.leosoliveres.chaldeas.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import fr.spc.leosoliveres.chaldeas.R
import fr.spc.leosoliveres.chaldeas.model.Site
import fr.spc.leosoliveres.chaldeas.view.adapter.ExpandableDynamicFormAdapter
import fr.spc.leosoliveres.chaldeas.viewmodel.SiteDetailViewModel
import fr.spc.leosoliveres.chaldeas.viewmodel.factory.SiteDetailViewModelFactory
import kotlinx.android.synthetic.main.fragment_site_detail.*
import org.json.JSONArray

class SiteDetailFragment : Fragment(R.layout.fragment_site_list) {
	private lateinit var viewModelFactory:SiteDetailViewModelFactory
	private lateinit var viewModel: SiteDetailViewModel
	private lateinit var listAdapter:ExpandableDynamicFormAdapter

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
		// Inflate the layout for this fragment
		val site = requireArguments().getParcelable<Site>("site")!!
		viewModelFactory = SiteDetailViewModelFactory(requireContext(),site)
		viewModel = viewModelFactory.create(SiteDetailViewModel::class.java)

		return inflater.inflate(R.layout.fragment_site_detail, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		listAdapter = ExpandableDynamicFormAdapter(requireContext(),viewModel.families)
		dynamic_form.setAdapter(listAdapter)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		fillSiteGeneralInfo(viewModel.site)
	}

	private fun fillSiteGeneralInfo(site:Site) {
		name.text = site.name
		infrastructure_type.text = site.type
		longitude.text = site.lonString()
		latitude.text = site.latString()
		owner.text = site.owner
		access.text = site.access
		power_source.text = site.powerSource
	}
}
