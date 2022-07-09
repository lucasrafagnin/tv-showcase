package com.rafagnin.tvshowcase.presentation.state

import com.rafagnin.tvshowcase.domain.model.ShowModel

sealed class HomeState {
    object Error : HomeState()
    object Loading : HomeState()
    data class ShowsLoaded(
        val items: List<ShowModel>?
    ) : HomeState()
}
