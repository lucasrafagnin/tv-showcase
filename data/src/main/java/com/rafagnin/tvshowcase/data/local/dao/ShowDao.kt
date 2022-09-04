package com.rafagnin.tvshowcase.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rafagnin.tvshowcase.data.model.local.LocalShowModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ShowDao {

    @Query("SELECT * FROM shows ORDER BY shows.name")
    fun getAll(): Flow<List<LocalShowModel>>

    @Insert
    fun insert(vararg model: LocalShowModel)

    @Delete
    fun delete(model: LocalShowModel)

    @Query("SELECT EXISTS(SELECT * FROM shows WHERE id = :id)")
    fun exist(id: Long): Boolean
}
