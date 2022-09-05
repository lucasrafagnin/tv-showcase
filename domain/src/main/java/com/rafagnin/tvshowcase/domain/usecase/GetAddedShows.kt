package com.rafagnin.tvshowcase.domain.usecase

import com.rafagnin.tvshowcase.domain.data.ShowRepository
import com.rafagnin.tvshowcase.domain.model.ShowModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAddedShows @Inject constructor(
    private val repository: ShowRepository,
) {

    operator fun invoke(id: Long? = null): Flow<List<ShowModel>> {
        return repository.getAddedShows(id)
    }
}
