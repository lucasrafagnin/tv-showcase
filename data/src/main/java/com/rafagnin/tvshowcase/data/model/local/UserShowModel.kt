package com.rafagnin.tvshowcase.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_shows")
data class UserShowModel(
    @PrimaryKey val id: Long,
    @ColumnInfo val name: String?,
    @ColumnInfo val image: String?,
    @ColumnInfo val favorite: Boolean = false,
    @ColumnInfo val added: Boolean = false,
)
