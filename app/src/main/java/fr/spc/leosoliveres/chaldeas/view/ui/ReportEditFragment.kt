package fr.spc.leosoliveres.chaldeas.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import fr.spc.leosoliveres.chaldeas.R
import fr.spc.leosoliveres.chaldeas.view.adapter.MeasuresAdapter
import fr.spc.leosoliveres.chaldeas.model.Measure
import kotlinx.android.synthetic.main.report_edit_fragment.*

class ReportEditFragment : Fragment(R.layout.report_edit_fragment) {

	companion object {
		fun newInstance() = ReportEditFragment()
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.report_edit_fragment, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		// TODO: Use the ViewModel

		measureRecyclerView.layoutManager = LinearLayoutManager(activity)
		measureRecyclerView.adapter = MeasuresAdapter(initVariables())
	}

	private fun initVariables(family:String="Faisceau 1",count:Int=20):ArrayList<Measure>{
		val arrayList = ArrayList<Measure>()
		for(i in 0..count){
			arrayList.add(
				Measure(
					String.format("Mesure n°$i - famille $family"),
					String.format("Unité $i"),
					String.format("U$i")
				)
			)
		}
		return arrayList
	}
}
