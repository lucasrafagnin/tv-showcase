package com.rafagnin.tvshowcase.data.repository

import com.rafagnin.tvshowcase.data.local.LocalDataSource
import com.rafagnin.tvshowcase.data.mapper.EpisodeToDomainMapper
import com.rafagnin.tvshowcase.data.mapper.ShowToDomainMapper
import com.rafagnin.tvshowcase.data.remote.RemoteDataSource
import com.rafagnin.tvshowcase.domain.data.ShowRepository
import com.rafagnin.tvshowcase.domain.model.EpisodeModel
import com.rafagnin.tvshowcase.domain.model.ShowDetailModel
import com.rafagnin.tvshowcase.domain.model.ShowModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ShowRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val showToDomainMapper: ShowToDomainMapper,
    private val episodeToDomainMapper: EpisodeToDomainMapper
) : ShowRepository {

    override fun getFavorites(): Flow<List<ShowModel>> = localDataSource.getFavorites()
        .map { it.map { model -> showToDomainMapper.map(model) } }

    override suspend fun getShows(): List<ShowModel> {
        return remoteDataSource.getShows().map { showToDomainMapper.map(it) }
    }

    override suspend fun searchShows(query: String): List<ShowModel> {
        return remoteDataSource.search(query).map { showToDomainMapper.map(it) }
    }

    override suspend fun getShowDetail(id: Long): ShowDetailModel {
        return remoteDataSource.getShowDetail(id).let { showToDomainMapper.mapDetail(it) }
    }

    override suspend fun getSchedule(date: String): List<EpisodeModel> {
        return remoteDataSource.getSchedule(date).map { episodeToDomainMapper.map(it) }
    }
}
