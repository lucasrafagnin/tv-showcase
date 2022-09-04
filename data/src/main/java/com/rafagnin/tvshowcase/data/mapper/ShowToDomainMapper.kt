package com.rafagnin.tvshowcase.data.mapper

import com.rafagnin.tvshowcase.data.ext.parseHtml
import com.rafagnin.tvshowcase.data.model.json.ScheduleJson
import com.rafagnin.tvshowcase.data.model.json.ShowJson
import com.rafagnin.tvshowcase.data.model.local.UserShowModel
import com.rafagnin.tvshowcase.domain.model.ShowDetailModel
import com.rafagnin.tvshowcase.domain.model.ShowModel
import javax.inject.Inject

class ShowToDomainMapper @Inject constructor(
    private val characterToDomainMapper: CharacterToDomainMapper,
    private val episodeToDomainMapper: EpisodeToDomainMapper
) {

    fun map(json: ShowJson) = ShowModel(
        id = json.id,
        name = json.name,
        image = json.image?.medium
    )

    /**
     * UserShowModel
     */

    fun map(model: ShowDetailModel) = UserShowModel(
        id = model.id,
        name = model.name,
        image = model.image,
        added = model.added,
        favorite = model.favorite
    )

    fun map(local: UserShowModel) = ShowModel(
        id = local.id,
        name = local.name,
        image = local.image,
        added = local.added,
        favorite = local.favorite
    )

    fun mapDetail(json: ShowJson) = ShowDetailModel(
        id = json.id,
        name = json.name,
        image = json.image?.medium,
        description = json.summary.parseHtml(),
        averageRuntime = json.averageRuntime,
        genres = json.genres?.joinToString(", "),
        status = json.status,
        rating = json.rating?.average,
        network = json.webChannel?.name ?: json.network?.name,
        characters = json.embedded?.cast?.map { characterToDomainMapper.map(it) },
        time = parseSchedule(json.schedule),
        seasons = json.embedded?.episodes?.map { episodeToDomainMapper.map(it) }
            ?.groupBy { it.season!! },
        favorite = false,
        added = false
    )

    private fun parseSchedule(schedule: ScheduleJson?) = schedule?.let {
        val time = if (!it.time.isNullOrEmpty()) it.time.plus("h, ") else ""
        time.plus(it.days?.joinToString(", "))
    }
}
