package com.rafagnin.tvshowcase.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafagnin.tvshowcase.domain.Resource
import com.rafagnin.tvshowcase.domain.usecase.SearchShow
import com.rafagnin.tvshowcase.presentation.action.SearchAction
import com.rafagnin.tvshowcase.presentation.state.SearchState
import com.rafagnin.tvshowcase.presentation.state.SearchState.Loading
import com.rafagnin.tvshowcase.presentation.state.SearchState.Error
import com.rafagnin.tvshowcase.presentation.state.SearchState.ShowsLoaded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchShow: SearchShow
) : ViewModel() {

    val actionFlow = MutableSharedFlow<SearchAction>()
    private val state: MutableStateFlow<SearchState> = MutableStateFlow(Loading)
    val _state: StateFlow<SearchState> = state

    private var lastSearch = ""

    init {
        viewModelScope.launch {
            handleActions()
        }
    }

    private fun getShows(text: String) = viewModelScope.launch {
        lastSearch = text
        when (val shows = searchShow.invoke(query = text)) {
            is Resource.Success -> state.value = ShowsLoaded(text, shows.data)
            is Resource.Loading -> state.value = Loading
            is Resource.Error -> state.value = Error
        }
    }

    private suspend fun handleActions() {
        actionFlow.collect {
            when (it) {
                is SearchAction.Query -> {
                    state.value = Loading
                    getShows(it.text)
                }
                is SearchAction.Retry -> {
                    state.value = Loading
                    getShows(lastSearch)
                }
            }
        }
    }
}
