package com.rafagnin.tvshowcase.domain.model

data class ShowDetailModel(
    val id: Long,
    val name: String?,
    val image: String?,
    val description: String?,
    val rating: Float?,
    val genres: List<String>?,
    val averageRuntime: Int?,
    val status: String?,
    val favorite: Boolean
)
