package com.rafagnin.tvshowcase.presentation.state

import com.rafagnin.tvshowcase.domain.model.EpisodeModel

sealed class ScheduleState {
    object Loading : ScheduleState()
    object Error : ScheduleState()
    data class EpisodesLoaded(
        val items: List<EpisodeModel>?
    ) : ScheduleState()
}
