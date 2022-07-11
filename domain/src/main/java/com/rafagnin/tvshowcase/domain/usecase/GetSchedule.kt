package com.rafagnin.tvshowcase.domain.usecase

import com.rafagnin.tvshowcase.domain.Resource
import com.rafagnin.tvshowcase.domain.common.DateHelper
import com.rafagnin.tvshowcase.domain.data.ShowRepository
import com.rafagnin.tvshowcase.domain.model.EpisodeModel
import javax.inject.Inject

class GetSchedule @Inject constructor(
    private val repository: ShowRepository,
) {

    suspend operator fun invoke(): Resource<List<EpisodeModel>> {
        return try {
            Resource.Success(repository.getSchedule(DateHelper.getTodayDate()))
        } catch (e: Exception) {
            Resource.Error(e.message)
        }
    }
}
