package com.rafagnin.tvshowcase.data.local.dao

import androidx.room.*
import com.rafagnin.tvshowcase.data.model.local.UserShowModel

@Dao
interface UserShowDao {

    @Query("SELECT * FROM user_shows WHERE user_shows.id = :id ORDER BY user_shows.name")
    fun getById(id: Long): List<UserShowModel>

    @Query("SELECT * FROM user_shows WHERE user_shows.added = 1 ORDER BY user_shows.name")
    fun getAdded(): List<UserShowModel>

    @Query("SELECT * FROM user_shows WHERE user_shows.favorite = 1 ORDER BY user_shows.name")
    fun getFavorites(): List<UserShowModel>

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
