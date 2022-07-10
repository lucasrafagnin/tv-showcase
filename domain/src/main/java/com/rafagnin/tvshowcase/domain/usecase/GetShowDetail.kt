package com.rafagnin.tvshowcase.domain.usecase

import com.rafagnin.tvshowcase.domain.Resource
import com.rafagnin.tvshowcase.domain.data.ShowRepository
import com.rafagnin.tvshowcase.domain.model.ShowDetailModel
import javax.inject.Inject

class GetShowDetail @Inject constructor(
    private val repository: ShowRepository,
    private val isShowFavorite: IsShowFavorite
) {

    suspend operator fun invoke(id: Long): Resource<ShowDetailModel> {
        return try {
            Resource.Success(
                repository.getShowDetail(id).copy(
                    favorite = isShowFavorite.invoke(id).data == true
                )
            )
        } catch (exception: Exception) {
            Resource.Error(exception.message)
        }
    }
}
