package fr.spc.leosoliveres.chaldeas.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import fr.spc.leosoliveres.chaldeas.R
import fr.spc.leosoliveres.chaldeas.model.Site
import kotlinx.android.synthetic.main.fragment_site_detail.*

class SiteDetailFragment : Fragment(R.layout.fragment_site_list) {
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_site_detail, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		val site = requireArguments().getParcelable<Site>("site")!!

		name.text = site.name
		infrastructure_type.text = site.type
		longitude.text = site.lonString()
		latitude.text = site.latString()
		owner.text = site.owner
		access.text = site.access
		power_source.text = site.powerSource
	}
}
