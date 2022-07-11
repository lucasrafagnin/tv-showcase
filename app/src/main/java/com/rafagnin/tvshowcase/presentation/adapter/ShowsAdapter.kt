package com.rafagnin.tvshowcase.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rafagnin.tvshowcase.databinding.ItemShowBinding
import com.rafagnin.tvshowcase.domain.model.ShowModel
import com.rafagnin.tvshowcase.presentation.adapter.ShowsAdapter.ShowViewHolder
import javax.inject.Inject

class ShowsAdapter @Inject constructor(
    private val callback: AdapterCallback
): PagingDataAdapter<ShowModel, ShowViewHolder>(GameDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val view = ItemShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, callback) }
    }

    class ShowViewHolder(private val view: ItemShowBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(item: ShowModel, callback: AdapterCallback) {
            view.image.load(item.image) {
                crossfade(true)
            }
            view.name.text = item.name
            view.root.setOnClickListener {
                callback.onShowClick(item.id)
            }
        }
    }

    interface AdapterCallback {
        fun onShowClick(id: Long)
    }
}
