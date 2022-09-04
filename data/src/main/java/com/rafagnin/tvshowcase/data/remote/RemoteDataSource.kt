package com.rafagnin.tvshowcase.data.remote

import com.rafagnin.tvshowcase.data.model.json.EpisodeJson
import com.rafagnin.tvshowcase.data.model.json.SearchJson
import com.rafagnin.tvshowcase.data.model.json.ShowJson

interface RemoteDataSource {
    suspend fun search(query: String): List<SearchJson>
    suspend fun getShows(page: Int): List<ShowJson>
    suspend fun getShowDetail(id: Long): ShowJson
    suspend fun getSchedule(date: String): List<EpisodeJson>
}
