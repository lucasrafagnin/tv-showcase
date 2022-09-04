package com.rafagnin.tvshowcase.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rafagnin.tvshowcase.data.local.dao.FavoriteDao
import com.rafagnin.tvshowcase.data.local.dao.ShowDao
import com.rafagnin.tvshowcase.data.model.local.LocalFavoriteModel
import com.rafagnin.tvshowcase.data.model.local.LocalShowModel

@Database(
    entities = [
        LocalFavoriteModel::class,
        LocalShowModel::class,
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun showDao(): ShowDao
}
