package com.rafagnin.tvshowcase.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rafagnin.tvshowcase.data.model.local.LocalFavoriteModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorites ORDER BY favorites.name")
    fun getAll(): Flow<List<LocalFavoriteModel>>

    @Insert
    fun insert(vararg model: LocalFavoriteModel)

    @Delete
    fun delete(model: LocalFavoriteModel)

    @Query("SELECT EXISTS(SELECT * FROM favorites WHERE id = :id)")
    fun exist(id: Long): Boolean
}
