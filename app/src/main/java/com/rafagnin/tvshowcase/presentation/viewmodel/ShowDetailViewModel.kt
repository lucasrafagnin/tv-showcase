package com.rafagnin.tvshowcase.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafagnin.tvshowcase.domain.Resource
import com.rafagnin.tvshowcase.domain.model.ShowDetailModel
import com.rafagnin.tvshowcase.domain.usecase.FavoriteShow
import com.rafagnin.tvshowcase.domain.usecase.GetShowDetail
import com.rafagnin.tvshowcase.presentation.action.ShowDetailAction
import com.rafagnin.tvshowcase.presentation.action.ShowDetailAction.Favorite
import com.rafagnin.tvshowcase.presentation.action.ShowDetailAction.Retry
import com.rafagnin.tvshowcase.presentation.state.ShowDetailState
import com.rafagnin.tvshowcase.presentation.state.ShowDetailState.Loaded
import com.rafagnin.tvshowcase.presentation.state.ShowDetailState.Loading
import com.rafagnin.tvshowcase.presentation.state.ShowDetailState.Error
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowDetailViewModel @Inject constructor(
    private val getShowDetail: GetShowDetail,
    private val favoriteShow: FavoriteShow
) : ViewModel() {

    private val state: MutableStateFlow<ShowDetailState> = MutableStateFlow(Loading)
    val _state: StateFlow<ShowDetailState> = state

    val actionFlow = MutableSharedFlow<ShowDetailAction>()

    init {
        viewModelScope.launch {
            handleActions()
        }
    }

    fun getShowDetail(id: Long) = viewModelScope.launch(Dispatchers.IO) {
        when (val result = getShowDetail.invoke(id)) {
            is Resource.Success -> state.value = Loaded(result.data)
            is Resource.Loading -> state.value = Loading
            is Resource.Error -> state.value = Error
        }
    }

    private fun favoriteShow(model: ShowDetailModel) = viewModelScope.launch(Dispatchers.IO) {
        when (val result = favoriteShow(model, !model.favorite)) {
            is Resource.Success -> state.value = Loaded(result.data)
            else -> state.value = Error
        }
    }

    private suspend fun handleActions() {
        actionFlow.collect {
            when (it) {
                is Favorite -> favoriteShow(it.model)
                is Retry -> getShowDetail(it.id)
            }
        }
    }
}
