package com.rafagnin.tvshowcase.domain.model

import java.io.Serializable

data class ShowModel(
    val id: Long,
    val name: String?,
    val image: String?,
    val favorite: Boolean? = false,
    val added: Boolean? = false,
) : Serializable
