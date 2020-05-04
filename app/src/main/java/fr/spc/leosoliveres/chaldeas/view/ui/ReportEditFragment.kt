package fr.spc.leosoliveres.chaldeas.view.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import fr.spc.leosoliveres.chaldeas.R
import fr.spc.leosoliveres.chaldeas.model.Family
import fr.spc.leosoliveres.chaldeas.model.Measure
import fr.spc.leosoliveres.chaldeas.view.adapter.MeasuresAdapter
import fr.spc.leosoliveres.chaldeas.viewmodel.ReportEditViewModel
import fr.spc.leosoliveres.chaldeas.viewmodel.ReportEditViewModelFactory
import kotlinx.android.synthetic.main.dialog_measure_edit.view.*
import kotlinx.android.synthetic.main.fragment_report_edit.*

class ReportEditFragment : Fragment(R.layout.fragment_report_edit){

	private lateinit var viewModel:ReportEditViewModel
	private lateinit var viewModelFactory:ReportEditViewModelFactory

	companion object {
		fun newInstance() = ReportEditFragment()
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
		viewModelFactory = ReportEditViewModelFactory()
		viewModel = ViewModelProviders.of(this,viewModelFactory).get(ReportEditViewModel::class.java)

		return inflater.inflate(R.layout.fragment_report_edit, container, false)
	}

	@SuppressLint("ResourceType")
	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		measureRecyclerView.layoutManager = LinearLayoutManager(activity)
		measureRecyclerView.adapter = MeasuresAdapter(viewModel.currentFamily.value!!._measures,this)

		family_spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item,viewModel.familiesToString())

		family_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
			//getitematposition
			override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
				Toast.makeText(context, parent?.getItemAtPosition(p2).toString(), Toast.LENGTH_LONG).show()
			}

			override fun onNothingSelected(parent: AdapterView<*>?) {

			}
		}

		viewModel.currentFamily.observe(viewLifecycleOwner, Observer { newFamily ->
			//TODO quand on change ou édite la mesure active
			updateMeasures(newFamily)
			updateFamilyName(newFamily.name)
		})

		viewModel.familyList.observe(viewLifecycleOwner, Observer { newFamilylist ->
			val strings = ArrayList<String>()
			for(i in 1..newFamilylist.count()) strings.add(newFamilylist[i-1].toString())
			updateFamilyList(strings)
		})

		rename_button.setOnClickListener {
			showFamilyRenameDialog(context)
		}
	}

	private fun updateMeasures(newFamily:Family) {
		measureRecyclerView.swapAdapter(MeasuresAdapter(newFamily.measures,this),true)
	}

	private fun updateFamilyName(newName:String) {
		family_name.text = newName
	}

	private fun updateFamilyList(list:ArrayList<String>) {
		family_spinner.adapter = ArrayAdapter(activity?.baseContext!!,android.R.layout.simple_spinner_dropdown_item,list)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		view.findViewById<Button>(R.id.add_measure).setOnClickListener {
			showMeasureCreationDialog(context)
		}
	}

	@SuppressLint("InflateParams")
	fun showMeasureCreationDialog(ctx: Context?){
		val builder = AlertDialog.Builder(ctx)
		val inflater = LayoutInflater.from(ctx)
		val dialogView = inflater.inflate(R.layout.dialog_measure_edit,null)

		builder.setView(dialogView)
		builder.setTitle(R.string.add_measure).apply{
			setPositiveButton(R.string.add) { _: DialogInterface, _: Int ->
				val newData = Measure(
					dialogView.measure_name.text.toString(),
					dialogView.unit_full.text.toString(),
					dialogView.unit_abriged.text.toString())
				viewModel.addMeasure(newData)
			}
			setNegativeButton(R.string.cancel) { dialog: DialogInterface, _:Int ->
				dialog.dismiss()
			}
		}
		builder.show()
	}

	private fun showFamilyRenameDialog(ctx:Context?) {
		val builder = AlertDialog.Builder(ctx)
		val dialogView = EditText(ctx)
		builder.setView(dialogView)
		builder.setTitle(R.string.rename).apply{
			setPositiveButton(R.string.rename) { _: DialogInterface, _: Int ->
				viewModel.renameFamily(dialogView.text.toString())
			}
			setNegativeButton(R.string.cancel) { dialog: DialogInterface, _:Int ->
				dialog.dismiss()
			}
		}
		builder.show()
	}
}
