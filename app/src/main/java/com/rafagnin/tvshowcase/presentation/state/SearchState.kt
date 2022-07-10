package com.rafagnin.tvshowcase.presentation.state

import com.rafagnin.tvshowcase.domain.model.ShowModel

sealed class SearchState {
    object Error : SearchState()
    object Loading : SearchState()
    data class ShowsLoaded(
        val query: String,
        val items: List<ShowModel>?
    ) : SearchState()
}
