package com.rafagnin.tvshowcase.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.rafagnin.tvshowcase.domain.usecase.GetAllShows
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllShows: GetAllShows
) : ViewModel() {

    fun getAllShows() = getAllShows.invoke()
}
