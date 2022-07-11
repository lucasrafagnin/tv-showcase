package com.rafagnin.tvshowcase.data.remote

import com.rafagnin.tvshowcase.data.model.EpisodeJson
import com.rafagnin.tvshowcase.data.model.SearchJson
import com.rafagnin.tvshowcase.data.model.ShowJson

interface RemoteDataSource {
    suspend fun search(query: String): List<SearchJson>
    suspend fun getShows(page: Int): List<ShowJson>
    suspend fun getShowDetail(id: Long): ShowJson
    suspend fun getSchedule(date: String): List<EpisodeJson>
}
