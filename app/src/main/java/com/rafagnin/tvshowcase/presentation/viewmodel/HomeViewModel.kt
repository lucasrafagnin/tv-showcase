package com.rafagnin.tvshowcase.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafagnin.tvshowcase.domain.usecase.GetAllShows
import com.rafagnin.tvshowcase.presentation.action.HomeAction
import com.rafagnin.tvshowcase.presentation.state.HomeState
import com.rafagnin.tvshowcase.presentation.state.HomeState.ShowsLoaded
import com.rafagnin.tvshowcase.presentation.state.HomeState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
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

    private fun getAllShows() = viewModelScope.launch(Dispatchers.IO) {
        getAllShows.invoke().collectLatest {
            state.value = ShowsLoaded(it)
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
