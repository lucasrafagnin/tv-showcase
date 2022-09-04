package com.rafagnin.tvshowcase.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shows")
data class LocalShowModel(
    @PrimaryKey val id: Long,
    @ColumnInfo val name: String?,
    @ColumnInfo val image: String?,
)
