package com.rafagnin.tvshowcase.domain.model

import java.io.Serializable

data class CharacterModel(
    val name: String?,
    val image: String?,
    val gender: String?,
    val country: String?,
    val birthday: String?,
    val site: String?
) : Serializable
