package com.rafagnin.tvshowcase.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rafagnin.tvshowcase.data.model.LocalShowModel

@Database(entities = [LocalShowModel::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun showDao(): ShowDao
}
