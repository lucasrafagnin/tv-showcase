package com.rafagnin.tvshowcase.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class LocalFavoriteModel(
    @PrimaryKey val id: Long,
    @ColumnInfo val name: String?,
    @ColumnInfo val image: String?,
)
