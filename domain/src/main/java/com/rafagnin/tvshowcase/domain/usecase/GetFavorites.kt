package com.rafagnin.tvshowcase.domain.usecase

import com.rafagnin.tvshowcase.domain.Resource
import com.rafagnin.tvshowcase.domain.data.ShowRepository
import com.rafagnin.tvshowcase.domain.model.ShowModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavorites @Inject constructor(
    private val repository: ShowRepository
) {

    operator fun invoke(): Flow<Resource<List<ShowModel>>> {
        return repository.getFavorites()
            .map { Resource.Success(it) }
            .catch { Resource.Error<List<ShowModel>>(it.message) }
    }
}
