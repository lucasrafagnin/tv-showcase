package com.rafagnin.tvshowcase.domain.usecase

import com.rafagnin.tvshowcase.domain.Resource
import com.rafagnin.tvshowcase.domain.data.ShowRepository
import javax.inject.Inject

class IsShowFavorite @Inject constructor(
    private val repository: ShowRepository
) {

    operator fun invoke(id: Long): Resource<Boolean> {
        return try {
            Resource.Success(repository.isShowFavorite(id))
        } catch (exception: Exception) {
            Resource.Success(false)
        }
    }
}
