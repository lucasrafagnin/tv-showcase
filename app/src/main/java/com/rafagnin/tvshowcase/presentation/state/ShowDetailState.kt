package com.rafagnin.tvshowcase.presentation.state

import com.rafagnin.tvshowcase.domain.model.ShowDetailModel

sealed class ShowDetailState {
    object Loading : ShowDetailState()
    object Error : ShowDetailState()
    data class Loaded(
        val show: ShowDetailModel?
    ) : ShowDetailState()
}
