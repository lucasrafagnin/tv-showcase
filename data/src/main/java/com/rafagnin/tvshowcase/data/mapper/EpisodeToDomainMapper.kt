package com.rafagnin.tvshowcase.data.mapper

import com.rafagnin.tvshowcase.data.model.EpisodeJson
import com.rafagnin.tvshowcase.domain.model.EpisodeModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class EpisodeToDomainMapper @Inject constructor(
    private val showToDomainMapper: ShowToDomainMapper
) {

    fun map(json: EpisodeJson) = EpisodeModel(
        id = json.id,
        name = json.name,
        image = json.image?.medium,
        season = json.season,
        episode = json.number,
        airdate = json.airdate,
        hourAirdate = json.airstamp?.let { getHourAirDate(it) },
        show = json.embedded?.show?.let { showToDomainMapper.map(it) }
    )

    private fun getHourAirDate(date: String): String {
        val currentSdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        val currentDate = currentSdf.parse(date)
        return sdf.format(currentDate?.time)
    }
}
