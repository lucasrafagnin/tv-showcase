package com.rafagnin.tvshowcase.domain.data

import com.rafagnin.tvshowcase.domain.model.EpisodeModel
import com.rafagnin.tvshowcase.domain.model.ShowDetailModel
import com.rafagnin.tvshowcase.domain.model.ShowModel
import kotlinx.coroutines.flow.Flow

interface ShowRepository {
    fun getFavorites(): Flow<List<ShowModel>>
    fun favoriteShow(model: ShowDetailModel, toFavorite: Boolean)
    fun isShowFavorite(id: Long): Boolean
    suspend fun searchShows(query: String): List<ShowModel>
    suspend fun getShows(): List<ShowModel>
    suspend fun getShowDetail(id: Long): ShowDetailModel
    suspend fun getSchedule(date: String): List<EpisodeModel>
}
