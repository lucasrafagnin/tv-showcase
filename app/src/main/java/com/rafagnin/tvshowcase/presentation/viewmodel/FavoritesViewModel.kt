package com.rafagnin.tvshowcase.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafagnin.tvshowcase.domain.Resource
import com.rafagnin.tvshowcase.domain.usecase.GetFavorites
import com.rafagnin.tvshowcase.presentation.action.FavoritesAction
import com.rafagnin.tvshowcase.presentation.state.FavoritesState
import com.rafagnin.tvshowcase.presentation.state.FavoritesState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavorites: GetFavorites
) : ViewModel() {

    private val state: MutableStateFlow<FavoritesState> = MutableStateFlow(Loading)
    val actionFlow = MutableSharedFlow<FavoritesAction>()
    val _state: StateFlow<FavoritesState> = state

    init {
        getGames()
        viewModelScope.launch {
            handleActions()
        }
    }

    private fun getGames() = viewModelScope.launch(Dispatchers.IO) {
        getFavorites.invoke()
            .catch { state.value = Error }
            .collect {
                when (it) {
                    is Resource.Success -> {
                        state.value =
                            if (it.data.isNullOrEmpty()) Empty
                            else ShowsLoaded(it.data)
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
                    getGames()
                }
            }
        }
    }
}
