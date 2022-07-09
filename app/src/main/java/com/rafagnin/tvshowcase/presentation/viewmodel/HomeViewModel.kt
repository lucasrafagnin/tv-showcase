package com.rafagnin.tvshowcase.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafagnin.tvshowcase.domain.Resource
import com.rafagnin.tvshowcase.domain.usecase.GetAllShows
import com.rafagnin.tvshowcase.presentation.action.HomeAction
import com.rafagnin.tvshowcase.presentation.state.HomeState
import com.rafagnin.tvshowcase.presentation.state.HomeState.ShowsLoaded
import com.rafagnin.tvshowcase.presentation.state.HomeState.Loading
import com.rafagnin.tvshowcase.presentation.state.HomeState.Error
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllShows: GetAllShows
) : ViewModel() {

    val actionFlow = MutableSharedFlow<HomeAction>()
    private val state: MutableStateFlow<HomeState> = MutableStateFlow(Loading)
    val _state: StateFlow<HomeState> = state

    init {
        getAllShows()
        viewModelScope.launch {
            handleActions()
        }
    }

    private fun getAllShows() = viewModelScope.launch {
        when (val shows = getAllShows.invoke()) {
            is Resource.Success -> state.value = ShowsLoaded(shows.data)
            is Resource.Loading -> state.value = Loading
            is Resource.Error -> state.value = Error
        }
    }

    private suspend fun handleActions() {
        actionFlow.collect {
            when (it) {
                HomeAction.Retry -> {
                    state.value = Loading
                    getAllShows()
                }
            }
        }
    }
}
