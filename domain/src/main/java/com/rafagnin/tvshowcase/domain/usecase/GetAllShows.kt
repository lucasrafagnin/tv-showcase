package com.rafagnin.tvshowcase.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rafagnin.tvshowcase.domain.data.ShowRepository
import com.rafagnin.tvshowcase.domain.model.ShowModel
import com.rafagnin.tvshowcase.domain.usecase.source.NETWORK_PAGE_SIZE
import com.rafagnin.tvshowcase.domain.usecase.source.ShowPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllShows @Inject constructor(
    private val repository: ShowRepository,
) {

    operator fun invoke(): Flow<PagingData<ShowModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ShowPagingSource(repository) }
        ).flow
    }
}
