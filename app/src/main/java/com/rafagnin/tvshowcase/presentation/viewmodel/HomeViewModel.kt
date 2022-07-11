package com.rafagnin.tvshowcase.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafagnin.tvshowcase.domain.usecase.GetAllShows
import com.rafagnin.tvshowcase.presentation.action.HomeAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllShows: GetAllShows
) : ViewModel() {

    val actionFlow = MutableSharedFlow<HomeAction>()

    init {
        viewModelScope.launch {
            handleActions()
        }
    }

    fun getAllShows() = getAllShows.invoke()

    private suspend fun handleActions() {
        actionFlow.collect {
            when (it) {
                HomeAction.Retry -> getAllShows()
            }
        }
    }
}
