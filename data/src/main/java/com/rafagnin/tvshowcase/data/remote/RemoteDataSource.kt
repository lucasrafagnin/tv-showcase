package com.rafagnin.tvshowcase.data.remote

import com.rafagnin.tvshowcase.data.remote.service.ApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: ApiService
) {
    suspend fun search(query: String) = api.search(query)

    suspend fun getShows() = api.getShows()

    suspend fun getShowDetail(id: Long) = api.getShowDetail(id)

    suspend fun getSchedule(date: String) = api.getSchedule(date)
}
