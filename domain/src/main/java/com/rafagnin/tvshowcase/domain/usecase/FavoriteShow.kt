package com.rafagnin.tvshowcase.domain.usecase

import com.rafagnin.tvshowcase.domain.Resource
import com.rafagnin.tvshowcase.domain.data.ShowRepository
import com.rafagnin.tvshowcase.domain.model.ShowDetailModel
import javax.inject.Inject

class FavoriteShow @Inject constructor(
    private val repository: ShowRepository
) {

    operator fun invoke(model: ShowDetailModel, toFavorite: Boolean): Resource<ShowDetailModel> {
        return try {
            repository.favoriteShow(model, toFavorite)
            Resource.Success(model.copy(favorite = toFavorite))
        } catch (exception: Exception) {
            Resource.Error(exception.message)
        }
    }
}
