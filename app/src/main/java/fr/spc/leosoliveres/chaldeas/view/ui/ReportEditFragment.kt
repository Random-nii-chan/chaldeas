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
import fr.spc.leosoliveres.chaldeas.viewmodel.factory.ReportEditViewModelFactory
import kotlinx.android.synthetic.main.dialog_measure_edit.view.*
import kotlinx.android.synthetic.main.fragment_report_edit.*
import kotlin.collections.ArrayList

//fragment d'édition du rapport
class ReportEditFragment : Fragment(R.layout.fragment_report_edit){

	private lateinit var viewModel:ReportEditViewModel
	private lateinit var viewModelFactory: ReportEditViewModelFactory

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
		//création du viewmodel
		viewModelFactory = ReportEditViewModelFactory(requireContext())
		viewModel = ViewModelProviders.of(this,viewModelFactory).get(ReportEditViewModel::class.java)

		return inflater.inflate(R.layout.fragment_report_edit, container, false)
	}

	//mise en place des fonctions appelées lors du clic sur bouton
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		add_measure.setOnClickListener {
			showMeasureCreationDialog(context)
		}

		save_button.setOnClickListener { _ ->
			viewModel.saveJson(requireContext())
		}

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

	@SuppressLint("ResourceType")
	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		//mise en place des adpatateurs
		measure_recyclerview.layoutManager = LinearLayoutManager(activity)
		measure_recyclerview.adapter = MeasuresAdapter(viewModel.currentFamily.value!!.measures,this)
		family_spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item,viewModel.familiesToString())
		//mise en place du comportement du spinner (menu déroulant)
		family_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
			override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
				viewModel.changeFamily(position)
			}
			override fun onNothingSelected(parent: AdapterView<*>?) {}
		}

		//mise en place des observateurs
		viewModel.currentFamily.observe(viewLifecycleOwner, Observer { newFamily ->
			updateMeasures(newFamily)
			updateFamilyName(newFamily.name)
		})

		viewModel.familyList.observe(viewLifecycleOwner, Observer { newFamilylist ->
			val strings=ArrayList<String>()
			if(newFamilylist.size==0) {
				strings.add(resources.getString(R.string.no_families))
				family_name_bar.visibility = View.INVISIBLE
				delete_family.visibility = View.INVISIBLE
				measure_recyclerview.visibility = View.INVISIBLE
			} else {
				family_name_bar.visibility = View.VISIBLE
				delete_family.visibility = View.VISIBLE
				measure_recyclerview.visibility = View.VISIBLE
				for(i in 0 until newFamilylist.count()-1) strings.add(newFamilylist[i].toString())
			}
			updateFamilyList(strings)
		})
	}

	//changement de l'adaptateur de la liste des mesures
	private fun updateMeasures(newFamily:Family) {
		measure_recyclerview.swapAdapter(MeasuresAdapter(newFamily.measures,this),true)
	}

	//renommer la famille en cours (uniquement le titre)
	private fun updateFamilyName(newName:String) {
		family_name.text = newName
	}

	//mettre à jour l'adaptateur du spinner de la liste des familles
	private fun updateFamilyList(list:ArrayList<String>) {
		family_spinner.adapter = ArrayAdapter(activity?.baseContext!!,android.R.layout.simple_spinner_dropdown_item,list)
		family_spinner.setSelection(viewModel.getFamilyIndex())
	}

	//affiche boîte de dialogue pour la création d'une mesure
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

	//affiche boîte de dialogue renommage de la famille en cours
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

	//affiche boîte de dialogue création d'une famille
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

	//affiche boîte de dialogue suppression d'une famille
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
