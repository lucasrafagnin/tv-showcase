package com.rafagnin.tvshowcase.presentation.action

import com.rafagnin.tvshowcase.domain.model.ShowDetailModel

sealed class ShowDetailAction {
    data class Retry(
        val id: Long
    ) : ShowDetailAction()
    data class Favorite(
        val model: ShowDetailModel
    ) : ShowDetailAction()
    data class Add(
        val model: ShowDetailModel
    ) : ShowDetailAction()
}
