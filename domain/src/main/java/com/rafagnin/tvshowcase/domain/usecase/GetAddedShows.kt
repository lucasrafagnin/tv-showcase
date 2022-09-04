package com.rafagnin.tvshowcase.domain.usecase

import com.rafagnin.tvshowcase.domain.Resource
import com.rafagnin.tvshowcase.domain.data.ShowRepository
import com.rafagnin.tvshowcase.domain.model.ShowModel
import javax.inject.Inject

class GetAddedShows @Inject constructor(
    private val repository: ShowRepository,
) {

    operator fun invoke(id: Long? = null): Resource<List<ShowModel>> {
        return Resource.Success(repository.getAddedShows(id))
    }
}
