package com.rafagnin.tvshowcase.data.local

import com.rafagnin.tvshowcase.data.model.LocalShowModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: ShowDao
) {
    fun getFavorites(): Flow<List<LocalShowModel>> = dao.getAll()
    fun exist(id: Long) = dao.exist(id)
    fun add(model: LocalShowModel) = dao.insert(model)
    fun remove(model: LocalShowModel) = dao.delete(model)
}
