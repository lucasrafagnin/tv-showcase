package com.rafagnin.tvshowcase.data.mapper

import com.rafagnin.tvshowcase.data.model.CastJson
import com.rafagnin.tvshowcase.domain.model.CharacterModel
import javax.inject.Inject

class CharacterToDomainMapper @Inject constructor() {

    fun map(json: CastJson) = CharacterModel(
        name = json.person?.name?.split(" ")?.firstOrNull(),
        image = json.person?.image?.medium,
    )
}
