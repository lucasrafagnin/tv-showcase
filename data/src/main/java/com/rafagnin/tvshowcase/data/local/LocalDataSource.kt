package com.rafagnin.tvshowcase.data.local

import com.rafagnin.tvshowcase.data.local.dao.UserShowDao
import com.rafagnin.tvshowcase.data.model.local.UserShowModel
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: UserShowDao,
) {
    fun getFavorites(): List<UserShowModel> = dao.getFavorites()
    fun getShows(id: Long?): List<UserShowModel> = id?.let { dao.getById(it) } ?: dao.getAdded()
    fun isFavorite(id: Long) = dao.isFavorite(id)
    fun addShow(model: UserShowModel) = dao.insert(model)
    fun updateShow(model: UserShowModel) = dao.update(model)
    fun removeShow(model: UserShowModel) = dao.delete(model)
}
