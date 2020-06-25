package fr.spc.leosoliveres.chaldeas.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer

import fr.spc.leosoliveres.chaldeas.R
import fr.spc.leosoliveres.chaldeas.model.Site
import fr.spc.leosoliveres.chaldeas.view.adapter.ExpandableDynamicFormAdapter
import fr.spc.leosoliveres.chaldeas.viewmodel.SiteDetailViewModel
import fr.spc.leosoliveres.chaldeas.viewmodel.factory.SiteDetailViewModelFactory
import kotlinx.android.synthetic.main.fragment_site_detail.*

//fragment d'inspection d'un site/création d'un rapport pour ce site
class SiteDetailFragment : Fragment(R.layout.fragment_site_list) {
	private lateinit var viewModelFactory:SiteDetailViewModelFactory
	private lateinit var viewModel: SiteDetailViewModel
	private lateinit var listAdapter:ExpandableDynamicFormAdapter

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
		//obtention du site à afficher avec les arguments
		val site = requireArguments().getParcelable<Site>("site")!!
		//création du viewmodel
		viewModelFactory = SiteDetailViewModelFactory(requireActivity().application,site)
		viewModel = viewModelFactory.create(SiteDetailViewModel::class.java)

		return inflater.inflate(R.layout.fragment_site_detail, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		//TODO mettre en place l'adaptateur du dernier rapport créé
		//mettre en place l'adaptateur du formulaire dynamique de création du rapport
		listAdapter = ExpandableDynamicFormAdapter(requireContext(),viewModel.formTemplate)
		dynamic_form.setAdapter(listAdapter)
		report_selector_spinner.adapter = ArrayAdapter(
			requireContext(),
			android.R.layout.simple_spinner_dropdown_item,
			viewModel.getPreviousReportsSummaries()
		)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		fillSiteGeneralInfo(viewModel.site)
		//observateur sur la liste des rapports précédemments enregistrés
		viewModel.previousReports.observe(viewLifecycleOwner, Observer { newReports ->
			val strings = ArrayList<String>()
			if(newReports.isEmpty()) {
				strings.add("Aucun rapport créé pour cette station")
			} else {
				for(i in newReports) strings.add(i.report.summary())
			}
			updateReportSummary(strings)
		})

		//clic sur le bouton d'enregistrement du rapport
		save_report.setOnClickListener {
			viewModel.saveReport()
		}
	}

	//changer l'adaptateur du spinner des rapports précédents
	private fun updateReportSummary(strings:List<String>) {
		report_selector_spinner.adapter = ArrayAdapter(
			requireContext(),
			android.R.layout.simple_spinner_dropdown_item,
			strings
		)
	}

	//remplir les informations générales du site
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
