package com.rafagnin.tvshowcase.domain.usecase

import com.rafagnin.tvshowcase.domain.Resource
import com.rafagnin.tvshowcase.domain.data.ShowRepository
import com.rafagnin.tvshowcase.domain.model.ShowModel
import javax.inject.Inject

class GetAllShows @Inject constructor(
    private val repository: ShowRepository,
) {

    suspend operator fun invoke(): Resource<List<ShowModel>> {
        return try {
            Resource.Success(repository.getShows())
        } catch (e: Exception) {
            Resource.Error(e.message)
        }
    }
}
