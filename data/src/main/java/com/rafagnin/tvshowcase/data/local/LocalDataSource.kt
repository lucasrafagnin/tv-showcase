package com.rafagnin.tvshowcase.data.local

import com.rafagnin.tvshowcase.data.local.dao.FavoriteDao
import com.rafagnin.tvshowcase.data.local.dao.ShowDao
import com.rafagnin.tvshowcase.data.model.local.LocalFavoriteModel
import com.rafagnin.tvshowcase.data.model.local.LocalShowModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val favoriteDao: FavoriteDao,
    private val showDao: ShowDao,
) {
    fun getFavorites(): Flow<List<LocalFavoriteModel>> = favoriteDao.getAll()
    fun getShows(): Flow<List<LocalShowModel>> = showDao.getAll()
    fun isFavorite(id: Long) = favoriteDao.exist(id)
    fun addFavorite(model: LocalFavoriteModel) = favoriteDao.insert(model)
    fun addShow(model: LocalShowModel) = showDao.insert(model)
    fun removeFavorite(model: LocalFavoriteModel) = favoriteDao.delete(model)
    fun removeShow(model: LocalShowModel) = showDao.delete(model)
}
