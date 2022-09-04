package com.rafagnin.tvshowcase.domain.usecase

import com.rafagnin.tvshowcase.domain.Resource
import com.rafagnin.tvshowcase.domain.data.ShowRepository
import com.rafagnin.tvshowcase.domain.model.ShowDetailModel
import javax.inject.Inject

class GetShowDetail @Inject constructor(
    private val repository: ShowRepository,
) {

    suspend operator fun invoke(id: Long): Resource<ShowDetailModel> {
        return try {
            val addedShows = repository.getAddedShows(id)
            Resource.Success(
                repository.getShowDetail(id).copy(
                    favorite = addedShows.firstOrNull()?.favorite == true,
                    added = addedShows.firstOrNull()?.added == true,
                )
            )
        } catch (exception: Exception) {
            Resource.Error(exception.message)
        }
    }
}
