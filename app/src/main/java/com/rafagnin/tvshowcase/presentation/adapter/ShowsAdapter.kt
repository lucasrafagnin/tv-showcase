package com.rafagnin.tvshowcase.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rafagnin.tvshowcase.databinding.ItemShowBinding
import com.rafagnin.tvshowcase.domain.model.ShowModel
import com.rafagnin.tvshowcase.presentation.adapter.ShowsAdapter.ShowViewHolder
import javax.inject.Inject

class ShowsAdapter @Inject constructor(
    private val callback: AdapterCallback
) : RecyclerView.Adapter<ShowViewHolder>() {

    private val list: MutableList<ShowModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val view = ItemShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.bind(list[position], callback)
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(items: List<ShowModel>?) {
        this.list.clear()
        this.list.addAll(items.orEmpty())
        notifyDataSetChanged()
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
