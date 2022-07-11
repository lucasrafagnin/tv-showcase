package com.rafagnin.tvshowcase.domain.common

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateHelper {

    private const val DEFAULT_FORMAT = "yyyy-MM-dd"
    private const val HOUR_FORMAT = "HH:mm"
    private const val API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ"

    fun getTodayDate(): String {
        val sdf = SimpleDateFormat(DEFAULT_FORMAT, Locale.getDefault())
        val calendar = Calendar.getInstance()
        return sdf.format(calendar.timeInMillis)
    }

    fun getHourAirDate(date: String): String {
        val currentSdf = SimpleDateFormat(API_DATE_FORMAT, Locale.getDefault())
        val sdf = SimpleDateFormat(HOUR_FORMAT, Locale.getDefault())
        val currentDate = currentSdf.parse(date)
        return sdf.format(currentDate?.time)
    }
}
