package com.rafagnin.tvshowcase.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rafagnin.tvshowcase.data.model.LocalShowModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ShowDao {

    @Query("SELECT * FROM show")
    fun getAll(): Flow<List<LocalShowModel>>

    @Insert
    fun insert(vararg model: LocalShowModel)

    @Delete
    fun delete(model: LocalShowModel)

    @Query("SELECT EXISTS(SELECT * FROM show WHERE id = :id)")
    fun exist(id: Long): Boolean
}
