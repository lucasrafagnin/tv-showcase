package com.rafagnin.tvshowcase.data.local.dao

import androidx.room.*
import com.rafagnin.tvshowcase.data.model.local.UserShowModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserShowDao {

    @Query("SELECT * FROM user_shows ORDER BY user_shows.name")
    fun getAll(): Flow<List<UserShowModel>>

    @Query("SELECT * FROM user_shows WHERE user_shows.id = :id ORDER BY user_shows.name")
    fun getById(id: Long): Flow<List<UserShowModel>>

    @Query("SELECT * FROM user_shows WHERE user_shows.favorite = 1 ORDER BY user_shows.name")
    suspend fun getFavorites(): List<UserShowModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg model: UserShowModel)

    @Update
    fun update(model: UserShowModel)

    @Delete
    fun delete(model: UserShowModel)

    @Query("SELECT EXISTS(SELECT * FROM user_shows WHERE id = :id)")
    fun exist(id: Long): Boolean

    @Query("SELECT EXISTS(SELECT * FROM user_shows WHERE id = :id AND favorite = 1)")
    fun isFavorite(id: Long): Boolean
}
