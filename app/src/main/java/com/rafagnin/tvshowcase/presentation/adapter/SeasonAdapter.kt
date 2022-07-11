package com.rafagnin.tvshowcase.presentation.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.rafagnin.tvshowcase.R
import com.rafagnin.tvshowcase.domain.model.EpisodeModel

class SeasonAdapter(
    context: Context,
    expandableListTitle: List<String>,
    expandableListDetail: Map<String, List<EpisodeModel>>
) : BaseExpandableListAdapter() {

    private val context: Context
    private var expandableListTitle: List<String>
    private var expandableListDetail: Map<String, List<EpisodeModel>>

    override fun getChild(listPosition: Int, expandedListPosition: Int): EpisodeModel? {
        return expandableListDetail[expandableListTitle[listPosition]]?.get(expandedListPosition)
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandableListDetail[expandableListTitle[listPosition]]?.get(expandedListPosition)?.id
            ?: 0
    }

    override fun getChildView(
        listPosition: Int,
        expandedListPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val model = getChild(listPosition, expandedListPosition) as EpisodeModel
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = convertView ?: inflater.inflate(R.layout.item_episode, parent, false)

        val episodeView = view.findViewById(R.id.episode) as TextView
        val nameView = view.findViewById(R.id.name) as TextView
        episodeView.text = context.getString(R.string.schedule_episode_fullprefix, model.episode)
        nameView.text = model.name
        return view
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return expandableListDetail[expandableListTitle[listPosition]]?.size ?: 0
    }

    override fun getGroup(listPosition: Int): Any {
        return expandableListTitle[listPosition]
    }

    override fun getGroupCount(): Int {
        return expandableListTitle.size
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getGroupView(
        listPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val season = getGroup(listPosition) as String
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = convertView ?: layoutInflater.inflate(R.layout.item_season, parent, false)
        val textView = view?.findViewById(R.id.season) as TextView
        textView.setTypeface(null, Typeface.BOLD)
        textView.text =
            layoutInflater.context.getString(R.string.schedule_season_fullprefix, season)
        return view
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }

    init {
        this.context = context
        this.expandableListTitle = expandableListTitle
        this.expandableListDetail = expandableListDetail
    }
}
