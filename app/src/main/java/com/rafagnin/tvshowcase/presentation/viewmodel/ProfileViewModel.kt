package com.rafagnin.tvshowcase.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafagnin.tvshowcase.domain.usecase.GetAddedShows
import com.rafagnin.tvshowcase.presentation.action.FavoritesAction
import com.rafagnin.tvshowcase.presentation.state.FavoritesState
import com.rafagnin.tvshowcase.presentation.state.FavoritesState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getAddedShows: GetAddedShows
) : ViewModel() {

    private val state: MutableStateFlow<FavoritesState> = MutableStateFlow(Loading)
    val actionFlow = MutableSharedFlow<FavoritesAction>()
    val _state: StateFlow<FavoritesState> = state

    init {
        viewModelScope.launch {
            handleActions()
        }
        viewModelScope.launch(Dispatchers.IO) {
            getShows()
        }
    }

    private suspend fun getShows() = getAddedShows()
        .collect {
            state.value =
                if (it.isEmpty()) Empty
                else ShowsLoaded(
                    favorites = it.filter { it.favorite == true },
                    addedShows = it.filter { it.added == true }
                )
        }

    private suspend fun handleActions() {
        actionFlow.collect {
            when (it) {
                FavoritesAction.Retry -> {
                    state.value = Loading
                    getShows()
                }
            }
        }
    }
}
