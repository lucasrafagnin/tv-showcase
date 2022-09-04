package com.rafagnin.tvshowcase.domain.usecase

import com.rafagnin.tvshowcase.domain.Resource
import com.rafagnin.tvshowcase.domain.data.ShowRepository
import com.rafagnin.tvshowcase.domain.model.ShowModel
import javax.inject.Inject

class GetFavorites @Inject constructor(
    private val repository: ShowRepository
) {

    operator fun invoke(): Resource<List<ShowModel>> {
        return Resource.Success(repository.getFavorites())
    }
}
