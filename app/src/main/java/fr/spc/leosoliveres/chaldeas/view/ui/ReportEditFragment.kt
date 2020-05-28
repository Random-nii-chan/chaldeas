package fr.spc.leosoliveres.chaldeas.view.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
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
import fr.spc.leosoliveres.chaldeas.viewmodel.factory.ReportEditViewModelFactory
import kotlinx.android.synthetic.main.dialog_measure_edit.view.*
import kotlinx.android.synthetic.main.fragment_report_edit.*
import kotlin.collections.ArrayList

class ReportEditFragment : Fragment(R.layout.fragment_report_edit){

	private lateinit var viewModel:ReportEditViewModel
	private lateinit var viewModelFactory: ReportEditViewModelFactory

	companion object {
		fun newInstance() = ReportEditFragment()
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
		//TODO : récupération des données depuis Room lors de l'arrivée
		viewModelFactory =
			ReportEditViewModelFactory(
				requireContext()
			)
		viewModel = ViewModelProviders.of(this,viewModelFactory).get(ReportEditViewModel::class.java)

		return inflater.inflate(R.layout.fragment_report_edit, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		view.findViewById<Button>(R.id.add_measure).setOnClickListener {
			showMeasureCreationDialog(context)
		}
	}

	@SuppressLint("ResourceType")
	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		measure_recyclerview.layoutManager = LinearLayoutManager(activity)
		measure_recyclerview.adapter = MeasuresAdapter(viewModel.currentFamily.value!!.measures,this)

		family_spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item,viewModel.familiesToString())

		family_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
			override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
				viewModel.changeFamily(position)
			}
			override fun onNothingSelected(parent: AdapterView<*>?) {}
		}

		save_button.setOnClickListener { _ ->
			viewModel.saveJson(requireContext())
		}


		viewModel.currentFamily.observe(viewLifecycleOwner, Observer { newFamily ->
			updateMeasures(newFamily)
			updateFamilyName(newFamily.name)
		})

		viewModel.familyList.observe(viewLifecycleOwner, Observer { newFamilylist ->
			val strings=ArrayList<String>()
			if(newFamilylist.size==0) {
				strings.add(resources.getString(R.string.no_families))
				//Log.i("array",defaults.toString())
				family_name_bar.visibility = View.INVISIBLE
				delete_family.visibility = View.INVISIBLE
				measure_recyclerview.visibility = View.INVISIBLE
			} else {
				family_name_bar.visibility = View.VISIBLE
				delete_family.visibility = View.VISIBLE
				measure_recyclerview.visibility = View.VISIBLE
				for(i in 0 until newFamilylist.count()-1) strings.add(newFamilylist[i].toString())
				updateFamilyList(strings)
			}
			Log.i("spinner",strings.toString())
			updateFamilyList(strings)
		})

		rename_button.setOnClickListener {
			showFamilyRenameDialog(context,viewModel.currentFamily.value!!.name)
		}

		add_family.setOnClickListener {
			showFamilyCreateDialog(context)
		}

		delete_family.setOnClickListener {
			showFamilyDeleteDialog(context,viewModel.currentFamily.value!!)
		}
	}

	private fun updateMeasures(newFamily:Family) {
		measure_recyclerview.swapAdapter(MeasuresAdapter(newFamily.measures,this),true)
	}

	private fun updateFamilyName(newName:String) {
		family_name.text = newName
	}

	private fun updateFamilyList(list:ArrayList<String>) {
		family_spinner.adapter = ArrayAdapter(activity?.baseContext!!,android.R.layout.simple_spinner_dropdown_item,list)
		family_spinner.setSelection(viewModel.getFamilyIndex())
	}

	@SuppressLint("InflateParams")
	private fun showMeasureCreationDialog(ctx: Context?){
		val builder = AlertDialog.Builder(ctx)
		val inflater = LayoutInflater.from(ctx)
		val dialogView = inflater.inflate(R.layout.dialog_measure_edit,null)

		builder.setView(dialogView)
		builder.setTitle(R.string.add_measure).apply{
			setPositiveButton(R.string.add) { _: DialogInterface, _: Int ->
				val newName = dialogView.measure_name.text.toString()
				val newUnit = dialogView.unit_full.text.toString()
				val newAbrigedUnit = dialogView.unit_abriged.text.toString()

				val newData = Measure(newName,newUnit,newAbrigedUnit)
				viewModel.addMeasure(newData)
			}
			setNegativeButton(R.string.cancel) { dialog: DialogInterface, _:Int ->
				dialog.dismiss()
			}
		}
		builder.show()
	}

	private fun showFamilyRenameDialog(ctx:Context?,autofill:String) {
		val builder = AlertDialog.Builder(ctx)
		val dialogView = EditText(ctx)
		builder.setView(dialogView)
		dialogView.setText(autofill)
		dialogView.setHint(R.string.hint_family_name)
		builder.setTitle(R.string.rename).apply{
			setPositiveButton(R.string.rename) { _: DialogInterface, _: Int ->
				viewModel.renameFamily(dialogView.text.toString())
				updateFamilyList(viewModel.familiesToString())
			}
			setNegativeButton(R.string.cancel) { dialog: DialogInterface, _:Int ->
				dialog.dismiss()
			}
		}
		builder.show()
	}

	private fun showFamilyCreateDialog(ctx:Context?) {
		val builder = AlertDialog.Builder(ctx)
		val dialogView = EditText(ctx)
		builder.setView(dialogView)
		dialogView.setHint(R.string.hint_family_name)
		builder.setTitle(R.string.add_family).apply{
			setPositiveButton(R.string.add) { _: DialogInterface, _: Int ->
				val title = dialogView.text.toString()
				viewModel.addFamily(Family(if(title.isEmpty() || title.isBlank()) "Nouvelle famille" else title))
				updateFamilyList(viewModel.familiesToString())
			}
			setNegativeButton(R.string.cancel) { dialog: DialogInterface, _:Int ->
				dialog.dismiss()
			}
		}
		builder.show()
	}

	private fun showFamilyDeleteDialog(ctx:Context?,f:Family) {
		val builder = AlertDialog.Builder(ctx)
		builder.setTitle(R.string.delete_family).apply{
			setPositiveButton(R.string.delete) { _: DialogInterface, _: Int ->
				viewModel.deleteFamily(f)
				updateFamilyList(viewModel.familiesToString())
			}
			setNegativeButton(R.string.cancel) { dialog: DialogInterface, _:Int ->
				dialog.dismiss()
			}
		}
		builder.show()
	}
}
