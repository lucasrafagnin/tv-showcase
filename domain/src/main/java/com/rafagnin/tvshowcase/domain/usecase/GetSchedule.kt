package com.rafagnin.tvshowcase.domain.usecase

import com.rafagnin.tvshowcase.domain.Resource
import com.rafagnin.tvshowcase.domain.data.ShowRepository
import com.rafagnin.tvshowcase.domain.model.EpisodeModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class GetSchedule @Inject constructor(
    private val repository: ShowRepository,
) {

    suspend operator fun invoke(): Resource<List<EpisodeModel>> {
        return try {
            Resource.Success(repository.getSchedule(getTodayDate()))
        } catch (e: Exception) {
            Resource.Error(e.message)
        }
    }

    private fun getTodayDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()
        return sdf.format(calendar.timeInMillis)
    }
}
