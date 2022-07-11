package com.rafagnin.tvshowcase.data.mapper

import com.rafagnin.tvshowcase.data.ext.parseHtml
import com.rafagnin.tvshowcase.data.model.EpisodeJson
import com.rafagnin.tvshowcase.data.model.ShowJson
import com.rafagnin.tvshowcase.domain.model.EpisodeModel
import com.rafagnin.tvshowcase.domain.model.ShowModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class EpisodeToDomainMapper @Inject constructor() {

    fun map(json: EpisodeJson) = EpisodeModel(
        id = json.id,
        name = json.name,
        description = json.summary.parseHtml(),
        image = json.image?.medium,
        season = json.season,
        episode = json.number,
        airdate = json.airdate,
        hourAirdate = json.airstamp?.let { getHourAirDate(it) },
        show = json.embedded?.show?.let { map(it) },
        runtime = json.runtime,
        site = json.url
    )

    fun map(json: ShowJson) = ShowModel(
        id = json.id,
        name = json.name,
        image = json.image?.medium
    )

    private fun getHourAirDate(date: String): String {
        val currentSdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        val currentDate = currentSdf.parse(date)
        return sdf.format(currentDate?.time)
    }
}
