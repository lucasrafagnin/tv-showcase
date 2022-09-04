package com.rafagnin.tvshowcase.data.mapper

import com.rafagnin.tvshowcase.data.model.json.CastJson
import com.rafagnin.tvshowcase.domain.model.CharacterModel
import javax.inject.Inject

class CharacterToDomainMapper @Inject constructor() {

    fun map(json: CastJson) = with(json.person) {
        CharacterModel(
            name = this?.name?.split(" ")?.firstOrNull(),
            image = this?.image?.medium,
            birthday = this?.birthday,
            gender = this?.gender,
            country = this?.country?.name,
            site = this?.url
        )
    }
}
