package com.rafagnin.tvshowcase.domain.usecase

import com.rafagnin.tvshowcase.domain.Resource
import com.rafagnin.tvshowcase.domain.data.ShowRepository
import com.rafagnin.tvshowcase.domain.model.ShowDetailModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetShowDetail @Inject constructor(
    private val repository: ShowRepository,
) {

    suspend operator fun invoke(id: Long): Flow<Resource<ShowDetailModel>> {
        return repository.getAddedShows(id)
            .map { added ->
                Resource.Success(
                    repository.getShowDetail(id).copy(
                        favorite = added.firstOrNull()?.favorite == true,
                        added = added.firstOrNull()?.added == true,
                    )
                )
            }
    }
}
