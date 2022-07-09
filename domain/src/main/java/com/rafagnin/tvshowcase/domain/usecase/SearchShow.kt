package com.rafagnin.tvshowcase.domain.usecase

import com.rafagnin.tvshowcase.domain.Resource
import com.rafagnin.tvshowcase.domain.data.ShowRepository
import com.rafagnin.tvshowcase.domain.model.ShowModel
import javax.inject.Inject

class SearchShow @Inject constructor(
    private val repository: ShowRepository,
) {

    suspend operator fun invoke(query: String): Resource<List<ShowModel>> {
        return try {
            Resource.Success(repository.searchShows(query))
        } catch (exception: Exception) {
            Resource.Error(exception.message)
        }
    }
}
