package com.rafagnin.tvshowcase.presentation.state

import com.rafagnin.tvshowcase.domain.model.ShowModel

sealed class FavoritesState {
    object Error : FavoritesState()
    object Loading : FavoritesState()
    object Empty : FavoritesState()
    data class ShowsLoaded(
        val items: List<ShowModel>?
    ) : FavoritesState()
}
