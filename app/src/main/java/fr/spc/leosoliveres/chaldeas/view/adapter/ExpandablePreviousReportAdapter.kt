package fr.spc.leosoliveres.chaldeas.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import fr.spc.leosoliveres.chaldeas.R
import fr.spc.leosoliveres.chaldeas.model.Record
import fr.spc.leosoliveres.chaldeas.model.relations.ReportWithRecords
import kotlinx.android.synthetic.main.expandable_header.view.*
import kotlinx.android.synthetic.main.expandable_report_line.view.*

class ExpandablePreviousReportAdapter(
	private val ctx: Context,
	private val content : ReportWithRecords
) : BaseExpandableListAdapter() {

	private val contents = content.getMap()
	private val headers = content.getHeaders()

	override fun getGroup(groupPosition: Int): String = headers[groupPosition]

	override fun isChildSelectable(groupPosition: Int, childPosition: Int) = true

	override fun hasStableIds() = false

	@SuppressLint("InflateParams")
	override fun getGroupView(
		groupPosition: Int,
		isExpanded: Boolean,
		_convertView: View?,
		parent: ViewGroup?
	): View {
		var convertView = _convertView
		val headerTitle = getGroup(groupPosition)
		if(convertView == null) {
			val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
			convertView = inflater.inflate(R.layout.expandable_header, null)
		}
		convertView!!.category_title.text =  headerTitle
		return convertView
	}

	override fun getChildrenCount(groupPosition: Int): Int {
		return contents[groupPosition].size
	}

	override fun getChild(groupPosition: Int, childPosition: Int): Record {
		return contents[groupPosition][childPosition]
	}

	override fun getGroupId(groupPosition: Int):Long =  groupPosition.toLong()

	@SuppressLint("InflateParams")
	override fun getChildView(
		groupPosition: Int,
		childPosition: Int,
		isExpanded: Boolean,
		_convertView: View?,
		parent: ViewGroup?
	): View {
		var convertView = _convertView
		val childRecord = getChild(groupPosition,childPosition)
		if(convertView == null) {
			val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
			convertView = inflater.inflate(R.layout.expandable_report_line,null)
		}
		convertView!!.contents.text = childRecord.export()
		return convertView
	}

	override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()

	override fun getGroupCount(): Int = headers.size
}