package fr.spc.leosoliveres.chaldeas.view.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import fr.spc.leosoliveres.chaldeas.R
import fr.spc.leosoliveres.chaldeas.model.entity.Measure
import fr.spc.leosoliveres.chaldeas.viewmodel.ReportEditViewModel
import fr.spc.leosoliveres.chaldeas.viewmodel.ReportEditViewModelFactory
import kotlinx.android.synthetic.main.dialog_measure_edit.view.*

class MeasuresAdapter(private val measures: ArrayList<Measure>?, private val context:Fragment) : RecyclerView.Adapter<MeasuresAdapter.ViewHolder>() {

	private var viewModelFactory: ReportEditViewModelFactory = ReportEditViewModelFactory(context.requireContext())
	private var viewModel:ReportEditViewModel = ViewModelProviders.of(this.context,viewModelFactory).get(ReportEditViewModel::class.java)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view:View = LayoutInflater.from(parent.context).inflate(R.layout.measure_row, parent, false)
		return ViewHolder(view)
	}

	override fun onBindViewHolder(holderSite: ViewHolder, position: Int) {
		val measure = measures?.get(position)
		val context = holderSite.itemView.context
		if (measure != null) {
			holderSite.bind(measure)
		}

		val item = measures?.get(holderSite.adapterPosition)

		holderSite.itemView.findViewById<Button>(R.id.button_delete).setOnClickListener {showAlertDelete(context,item!!)}

		holderSite.itemView.findViewById<Button>(R.id.button_edit).setOnClickListener {showAlertEdit(context,item!!)}

		holderSite.itemView.findViewById<Button>(R.id.button_duplicate).setOnClickListener { viewModel.duplicateMeasure(item!!) }
	}

	@SuppressLint("InflateParams")
	private fun showAlertEdit(ctx: Context,m: Measure){
		val builder = AlertDialog.Builder(ctx)
		val inflater = LayoutInflater.from(ctx)
		val dialogView = inflater.inflate(R.layout.dialog_measure_edit,null)

		autofill(dialogView,m)

		builder.setView(dialogView)
		builder.setTitle(R.string.edit_measure).apply{
			setPositiveButton(R.string.apply) { _: DialogInterface, _: Int ->
				val newData = Measure(
					dialogView.measure_name.text.toString(),
					dialogView.unit_full.text.toString(),
					dialogView.unit_abriged.text.toString()
				)
				viewModel.editMeasure(m,newData)
			}
			setNegativeButton(R.string.cancel) { dialog:DialogInterface, _:Int ->
				dialog.dismiss()
			}
		}
		builder.show()
	}

	@SuppressLint("InflateParams")
	private fun showAlertDelete(ctx:Context, m: Measure) {
		val builder = AlertDialog.Builder(ctx)

		builder.setTitle(R.string.delete_measure).apply{
			setPositiveButton(R.string.yes) { _: DialogInterface, _: Int ->
				viewModel.deleteMeasure(m)
			}
			setNegativeButton(R.string.no) { dialog:DialogInterface, _:Int ->
				dialog.dismiss()
			}
		}
		builder.show()
	}

	private fun autofill(mainView:View, measure: Measure){
		mainView.findViewById<EditText>(R.id.measure_name).setText(measure.name)
		mainView.findViewById<EditText>(R.id.unit_full).setText(measure.unitFull)
		mainView.findViewById<EditText>(R.id.unit_abriged).setText(measure.unitAbriged)
	}

	override fun getItemCount(): Int = measures!!.size

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		private val measureName: TextView = itemView.findViewById(R.id.measureName)
		private val measureUnit: TextView = itemView.findViewById(R.id.measureUnit)

		fun bind(measure: Measure){
			measureName.text = measure.name
			measureUnit.text = measure.allUnits()
		}
	}
}