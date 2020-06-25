package fr.spc.leosoliveres.chaldeas.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import fr.spc.leosoliveres.chaldeas.R
import fr.spc.leosoliveres.chaldeas.model.Family
import fr.spc.leosoliveres.chaldeas.model.Measure
import kotlinx.android.synthetic.main.expandable_dynamic_line.view.*
import kotlinx.android.synthetic.main.expandable_header.view.*

//adapter de la l'expandablelistview qui représente le rapport généré automatiquement
//TODO quand un champ de texte est créé,
class ExpandableDynamicFormAdapter(private val ctx: Context, _families:ArrayList<Family>) : BaseExpandableListAdapter() {

	private var families:ArrayList<Family> = _families

	//obtenir l'objet parent à la position précisée
	override fun getGroup(groupPosition: Int): String =
		families[groupPosition].name

	//ne pas bouger
	override fun isChildSelectable(p0: Int, p1: Int): Boolean = true

	//ne pas bouger
	override fun hasStableIds(): Boolean = false

	//obtenir le nombre d'enfants dans un groupe à une position donnée
	override fun getChildrenCount(groupPosition: Int): Int =
		families[groupPosition].measureCount()

	//obtenir un enfant à une position donné dans un groupe à une position donnée
	override fun getChild(groupPosition: Int, childPosition: Int): Measure =
		families[groupPosition].measures[childPosition]

	//ne pas bouger
	override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

	//retourner une View qui représente un élément enfant de la liste
	@SuppressLint("InflateParams")
	override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, cv: View?, parent: ViewGroup?): View {
		var convertView = cv
		val childMeasure = getChild(groupPosition,childPosition)
		if(convertView == null) {
			val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
			convertView = inflater.inflate(R.layout.expandable_dynamic_line,null)
		}

		convertView!!.record_label.text = childMeasure.nameAndAbriged()
		convertView.record_textfield.hint = childMeasure.unitFull

		return convertView
	}

	//retourner une view qui représente un élément parent de la liste
	@SuppressLint("InflateParams")
	override fun getGroupView(groupPosition:Int, expanded: Boolean, cv: View?, parent: ViewGroup?): View {
		//convertview permet de faire une sauvegarde
		var convertView = cv
		val headerTitle = getGroup(groupPosition)
		if(convertView == null) {
			val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
			convertView = inflater.inflate(R.layout.expandable_header,null)
		}

		convertView!!.category_title.text = headerTitle

		return convertView
	}

	//ne pas changer
	override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()

	//obtenir le nombre total de groupes
	override fun getGroupCount(): Int = families.size
}