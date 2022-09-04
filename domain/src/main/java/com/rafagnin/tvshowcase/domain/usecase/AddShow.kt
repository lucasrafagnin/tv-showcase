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
            val newModel = model.copy(added = toAdd)
            repository.addShow(newModel)
            Resource.Success(newModel)
        } catch (exception: Exception) {
            Resource.Error(exception.message)
        }
    }
}
