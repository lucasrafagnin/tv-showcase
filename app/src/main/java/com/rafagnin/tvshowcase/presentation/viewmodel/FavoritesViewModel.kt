package com.rafagnin.tvshowcase.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafagnin.tvshowcase.domain.Resource
import com.rafagnin.tvshowcase.domain.usecase.GetAddedShows
import com.rafagnin.tvshowcase.domain.usecase.GetFavorites
import com.rafagnin.tvshowcase.presentation.action.FavoritesAction
import com.rafagnin.tvshowcase.presentation.state.FavoritesState
import com.rafagnin.tvshowcase.presentation.state.FavoritesState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavorites: GetFavorites,
    private val getAddedShows: GetAddedShows
) : ViewModel() {

    private val state: MutableStateFlow<FavoritesState> = MutableStateFlow(Loading)
    val actionFlow = MutableSharedFlow<FavoritesAction>()
    val _state: StateFlow<FavoritesState> = state

    init {
        getShows()
        viewModelScope.launch {
            handleActions()
        }
    }

    private fun getShows() = viewModelScope.launch(Dispatchers.IO) {
        merge(getAddedShows(), getFavorites())
            .catch { state.value = Error }
            .collect {
                when (it) {
                    is Resource.Success -> {
                        state.value =
                            if (it.data.isNullOrEmpty()) Empty
                            else ShowsLoaded(
                                favorites = it.data?.filter { it.favorite == true },
                                addedShows = it.data?.filter { it.added == true }
                            )
                    }
                    is Resource.Loading -> state.value = Loading
                    is Resource.Error -> state.value = Error
                }
            }
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
