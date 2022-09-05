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

    override fun getAddedShows(id: Long?): Flow<List<ShowModel>> =
        localDataSource.getShows(id)
            .map {
                it.map { showToDomainMapper.map(it) }
            }

    override suspend fun getFavorites(): List<ShowModel> = localDataSource.getFavorites()
        .map { model -> showToDomainMapper.map(model) }

    override fun addShow(model: ShowDetailModel) {
        if (model.added) localDataSource.addShow(showToDomainMapper.map(model))
        else localDataSource.updateShow(showToDomainMapper.map(model))
    }

    override fun favoriteShow(model: ShowDetailModel) {
        if (model.favorite) localDataSource.addShow(showToDomainMapper.map(model))
        else localDataSource.updateShow(showToDomainMapper.map(model))
    }

    override fun isShowFavorite(id: Long) = localDataSource.isFavorite(id)

    override suspend fun getShows(page: Int): List<ShowModel> {
        return remoteDataSource.getShows(page).map { showToDomainMapper.map(it) }
    }

    override suspend fun searchShows(query: String): List<ShowModel> {
        return remoteDataSource.search(query).map { showToDomainMapper.map(it.show) }
    }

    override suspend fun getShowDetail(id: Long): ShowDetailModel {
        return remoteDataSource.getShowDetail(id).let { showToDomainMapper.mapDetail(it) }
    }

    override suspend fun getSchedule(date: String): List<EpisodeModel> {
        return remoteDataSource.getSchedule(date).map { episodeToDomainMapper.map(it) }
    }
}
