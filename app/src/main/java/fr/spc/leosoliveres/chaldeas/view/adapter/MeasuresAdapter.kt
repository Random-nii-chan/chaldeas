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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import fr.spc.leosoliveres.chaldeas.R
import fr.spc.leosoliveres.chaldeas.model.Measure
import fr.spc.leosoliveres.chaldeas.viewmodel.ReportEditViewModel

class MeasuresAdapter(private val measures: ArrayList<Measure>, context:Fragment) : RecyclerView.Adapter<MeasuresAdapter.ViewHolder>() {

	private lateinit var viewModel:ReportEditViewModel

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view:View = LayoutInflater.from(parent.context).inflate(R.layout.measure_row, parent, false)
		return ViewHolder(view)
	}

	override fun onBindViewHolder(holderSite: ViewHolder, position: Int) {
		val measure = measures[position]
		val context = holderSite.itemView.context
		holderSite.bind(measure)

		val item = measures[holderSite.adapterPosition]

		/*
		holderSite.itemView.findViewById<Button>(R.id.button_delete).setOnClickListener {
			Toast.makeText(context,"Suppression de ${item.name}",Toast.LENGTH_SHORT).show()
		}

		holderSite.itemView.findViewById<Button>(R.id.button_edit).setOnClickListener {
			val alertDialogBuilder = createAlertEdit(context,item)
			alertDialogBuilder.show()
		}
		 */
	}

	/*
	@SuppressLint("InflateParams")
	private fun createAlertEdit(ctx: Context,m:Measure):AlertDialog{
		val builder = AlertDialog.Builder(ctx)
		val inflater = LayoutInflater.from(ctx)
		val dialogView = inflater.inflate(R.layout.dialog_measure_edit,null)

		autofill(dialogView,m)

		builder.setView(dialogView)
		builder.setTitle(R.string.edit_measure).apply{
			setPositiveButton(R.string.apply) { _: DialogInterface, _: Int ->
				Toast.makeText(ctx,"Application des changements",Toast.LENGTH_SHORT).show()
			}
			setNegativeButton(R.string.cancel) { dialog:DialogInterface, _:Int ->
				Toast.makeText(ctx,"Annulation des changements",Toast.LENGTH_SHORT).show()
				dialog.cancel()
			}
		}
		return builder.create()
	}

	private fun autofill(mainView:View, measure:Measure){
		//trouver les enfants et les remplir des données de la mesure
		mainView.findViewById<EditText>(R.id.measure_name).setText(measure.name)
		mainView.findViewById<EditText>(R.id.unit_full).setText(measure.unitFull)
		mainView.findViewById<EditText>(R.id.unit_abriged).setText(measure.unitAbriged)
	}
	 */

	override fun getItemCount(): Int = measures.size

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		private val measureName: TextView = itemView.findViewById(R.id.measureName)
		private val measureUnit: TextView = itemView.findViewById(R.id.measureUnit)

		fun bind(measure: Measure){
			measureName.text = measure.name
			measureUnit.text = measure.allUnits()
		}
	}
}