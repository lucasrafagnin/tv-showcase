package com.rafagnin.tvshowcase.domain.data

import com.rafagnin.tvshowcase.domain.model.EpisodeModel
import com.rafagnin.tvshowcase.domain.model.ShowDetailModel
import com.rafagnin.tvshowcase.domain.model.ShowModel

interface ShowRepository {
    fun getAddedShows(id: Long?): List<ShowModel>
    fun getFavorites(): List<ShowModel>
    fun favoriteShow(model: ShowDetailModel)
    fun addShow(model: ShowDetailModel)
    fun isShowFavorite(id: Long): Boolean
    suspend fun searchShows(query: String): List<ShowModel>
    suspend fun getShows(page: Int): List<ShowModel>
    suspend fun getShowDetail(id: Long): ShowDetailModel
    suspend fun getSchedule(date: String): List<EpisodeModel>
}
