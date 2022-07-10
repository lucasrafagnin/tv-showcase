package com.rafagnin.tvshowcase.data.remote.service

import com.rafagnin.tvshowcase.data.model.EpisodeJson
import com.rafagnin.tvshowcase.data.model.SearchJson
import com.rafagnin.tvshowcase.data.model.ShowJson
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/shows")
    suspend fun search(
        @Query("q") query: String?
    ): List<SearchJson>

    @GET("shows")
    suspend fun getShows(): List<ShowJson>

    @GET("shows/{id}?embed[]=episodes&embed[]=cast")
    suspend fun getShowDetail(
        @Path("id") id: Long
    ): ShowJson

    @GET("schedule/web")
    suspend fun getSchedule(
        @Query("date") date: String
    ): List<EpisodeJson>
}
