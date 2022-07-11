package com.rafagnin.tvshowcase.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.rafagnin.tvshowcase.domain.model.ShowModel

class ShowDiffCallback : DiffUtil.ItemCallback<ShowModel>() {
    override fun areItemsTheSame(oldItem: ShowModel, newItem: ShowModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShowModel, newItem: ShowModel): Boolean {
        return oldItem == newItem
    }
}
