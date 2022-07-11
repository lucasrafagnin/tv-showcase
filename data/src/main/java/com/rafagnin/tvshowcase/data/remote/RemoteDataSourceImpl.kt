package com.rafagnin.tvshowcase.data.remote

import com.rafagnin.tvshowcase.data.remote.service.ApiService
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api: ApiService
) : RemoteDataSource {
    override suspend fun search(query: String) = api.search(query)

    override suspend fun getShows(page: Int) = api.getShows(page)

    override suspend fun getShowDetail(id: Long) = api.getShowDetail(id)

    override suspend fun getSchedule(date: String) = api.getSchedule(date)
}
