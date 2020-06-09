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
import kotlinx.android.synthetic.main.dynamic_form_element.view.*
import kotlinx.android.synthetic.main.dynamic_form_parent.view.*

class ExpandableDynamicFormAdapter(private val ctx: Context, _families:ArrayList<Family>) : BaseExpandableListAdapter() {

	private var families:ArrayList<Family> = _families

	override fun getGroup(groupPosition: Int): String =
		families[groupPosition].name

	override fun isChildSelectable(p0: Int, p1: Int): Boolean = true

	override fun hasStableIds(): Boolean = false

	override fun getChildrenCount(groupPosition: Int): Int =
		families[groupPosition].measureCount()

	override fun getChild(groupPosition: Int, childPosition: Int): Measure =
		families[groupPosition].measures[childPosition]

	override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

	@SuppressLint("InflateParams")
	override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, cv: View?, parent: ViewGroup?): View {
		var convertView = cv
		val childMeasure = getChild(groupPosition,childPosition)
		if(convertView == null) {
			val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
			convertView = inflater.inflate(R.layout.dynamic_form_element,null)
		}

		convertView!!.record_label.text = childMeasure.nameAndAbriged()
		convertView.record_textfield.hint = childMeasure.unitFull

		return convertView
	}

	@SuppressLint("InflateParams")
	override fun getGroupView(groupPosition:Int, expanded: Boolean, cv: View?, parent: ViewGroup?): View {
		//convertview permet de faire une sauvegarde
		var convertView = cv
		val headerTitle = getGroup(groupPosition)
		if(convertView == null) {
			val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
			convertView = inflater.inflate(R.layout.dynamic_form_parent,null)
		}

		convertView!!.category_title.text = headerTitle

		return convertView
	}

	override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()

	override fun getGroupCount(): Int = families.size
}