package com.rafagnin.tvshowcase.domain.usecase

import com.rafagnin.tvshowcase.domain.Resource
import com.rafagnin.tvshowcase.domain.data.ShowRepository
import com.rafagnin.tvshowcase.domain.model.ShowDetailModel
import javax.inject.Inject

class AddShow @Inject constructor(
    private val repository: ShowRepository
) {

    operator fun invoke(model: ShowDetailModel, toAdd: Boolean): Resource<ShowDetailModel> {
        return try {
            repository.addShow(model, toAdd)
            Resource.Success(model.copy(added = toAdd))
        } catch (exception: Exception) {
            Resource.Error(exception.message)
        }
    }
}
