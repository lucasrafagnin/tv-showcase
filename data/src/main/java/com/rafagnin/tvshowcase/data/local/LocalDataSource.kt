package com.rafagnin.tvshowcase.data.local

import com.rafagnin.tvshowcase.data.local.dao.UserShowDao
import com.rafagnin.tvshowcase.data.model.local.UserShowModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: UserShowDao,
) {
    suspend fun getFavorites(): List<UserShowModel> = dao.getFavorites()
    fun getShows(id: Long?): Flow<List<UserShowModel>> = id?.let { dao.getById(it) } ?: dao.getAll()
    fun isFavorite(id: Long) = dao.isFavorite(id)
    fun addShow(model: UserShowModel) = dao.insert(model)
    fun updateShow(model: UserShowModel) = dao.update(model)
    fun removeShow(model: UserShowModel) = dao.delete(model)
}
