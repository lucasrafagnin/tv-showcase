package com.rafagnin.tvshowcase.data.mapper

import com.rafagnin.tvshowcase.data.model.ShowJson
import com.rafagnin.tvshowcase.domain.model.ShowDetailModel
import com.rafagnin.tvshowcase.domain.model.ShowModel
import javax.inject.Inject

class ShowToDomainMapper @Inject constructor() {

    fun map(json: ShowJson) = ShowModel(
        id = json.id,
        name = json.name,
        image = json.image.medium
    )

    fun mapDetail(json: ShowJson) = ShowDetailModel(
        id = json.id,
        name = json.name,
        image = json.image.medium,
        description = json.summary
    )
}
