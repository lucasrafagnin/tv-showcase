package com.rafagnin.tvshowcase.data.model

import com.squareup.moshi.Json

data class ScheduleJson(
    @Json(name = "time") val time: String?,
    @Json(name = "days") val days: List<String>?,
)
