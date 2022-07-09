package com.rafagnin.tvshowcase.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafagnin.tvshowcase.databinding.ItemScheduleBinding
import com.rafagnin.tvshowcase.domain.model.EpisodeModel
import com.rafagnin.tvshowcase.presentation.adapter.ScheduleAdapter.ScheduleViewHolder

class ScheduleAdapter(
    private val callback: AdapterCallback
) : RecyclerView.Adapter<ScheduleViewHolder>() {

    private val list: MutableList<EpisodeModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val view = ItemScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScheduleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bind(list[position], callback)
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(items: List<EpisodeModel>?) {
        this.list.clear()
        this.list.addAll(items.orEmpty())
        notifyDataSetChanged()
    }

    class ScheduleViewHolder(private val view: ItemScheduleBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(item: EpisodeModel, callback: AdapterCallback) {
            view.name.text = item.name
            view.date.text = item.airdate
            view.season.text = item.season
            view.episode.text = item.number
            view.root.setOnClickListener {
                callback.onEpisodeClick(item.id)
            }
        }
    }

    interface AdapterCallback {
        fun onEpisodeClick(id: Long)
    }
}
