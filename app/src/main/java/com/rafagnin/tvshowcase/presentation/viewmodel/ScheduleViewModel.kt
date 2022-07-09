package com.rafagnin.tvshowcase.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafagnin.tvshowcase.domain.Resource
import com.rafagnin.tvshowcase.domain.usecase.GetSchedule
import com.rafagnin.tvshowcase.presentation.action.ScheduleAction
import com.rafagnin.tvshowcase.presentation.state.ScheduleState
import com.rafagnin.tvshowcase.presentation.state.ScheduleState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val getSchedule: GetSchedule
) : ViewModel() {

    val actionFlow = MutableSharedFlow<ScheduleAction>()
    private val state: MutableStateFlow<ScheduleState> = MutableStateFlow(Loading)
    val _state: StateFlow<ScheduleState> = state

    init {
        getSchedule()
        viewModelScope.launch {
            handleActions()
        }
    }

    private fun getSchedule() = viewModelScope.launch {
        when (val episodes = getSchedule.invoke()) {
            is Resource.Success -> state.value = EpisodesLoaded(episodes.data)
            is Resource.Loading -> state.value = Loading
            is Resource.Error -> state.value = Error
        }
    }

    private suspend fun handleActions() {
        actionFlow.collect {
            when (it) {
                ScheduleAction.Retry -> {
                    state.value = Loading
                    getSchedule()
                }
            }
        }
    }
}
