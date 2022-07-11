package com.rafagnin.tvshowcase.domain.model

data class ShowDetailModel(
    val id: Long,
    val name: String?,
    val image: String?,
    val description: String?,
    val rating: Float?,
    val genres: String?,
    val averageRuntime: Int?,
    val status: String?,
    val network: String?,
    val characters: List<CharacterModel>?,
    val time: String?,
    val seasons: Map<String, List<EpisodeModel>>?,
    val favorite: Boolean
)
