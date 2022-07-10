package com.rafagnin.tvshowcase.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "show")
data class LocalShowModel(
    @PrimaryKey val id: Long,
    @ColumnInfo val name: String?,
    @ColumnInfo val image: String?,
)
