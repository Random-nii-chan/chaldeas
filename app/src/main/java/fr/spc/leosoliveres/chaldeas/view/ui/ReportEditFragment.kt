package fr.spc.leosoliveres.chaldeas.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import fr.spc.leosoliveres.chaldeas.R
import fr.spc.leosoliveres.chaldeas.view.adapter.MeasuresAdapter
import fr.spc.leosoliveres.chaldeas.model.Measure
import fr.spc.leosoliveres.chaldeas.viewmodel.ReportEditViewModel
import kotlinx.android.synthetic.main.report_edit_fragment.*
import java.util.EnumSet.of
import java.util.Optional.of

class ReportEditFragment : Fragment(R.layout.report_edit_fragment) {

	private lateinit var viewModel:ReportEditViewModel

	companion object {
		fun newInstance() = ReportEditFragment()
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.report_edit_fragment, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		measureRecyclerView.layoutManager = LinearLayoutManager(activity)
		measureRecyclerView.adapter = MeasuresAdapter(viewModel.initVariables())

		viewModel = ViewModelProviders.of(this).get(ReportEditViewModel::class.java)
	}
}
