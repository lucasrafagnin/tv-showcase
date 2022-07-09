package com.rafagnin.tvshowcase.data.mapper

import com.rafagnin.tvshowcase.data.model.EpisodeJson
import com.rafagnin.tvshowcase.domain.model.EpisodeModel
import javax.inject.Inject

class EpisodeToDomainMapper @Inject constructor() {

    fun map(json: EpisodeJson) = EpisodeModel(
        id = json.id,
        name = json.name,
        image = json.image.medium
    )
}
