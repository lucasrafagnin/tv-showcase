package com.rafagnin.tvshowcase.domain.usecase.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rafagnin.tvshowcase.domain.data.ShowRepository
import com.rafagnin.tvshowcase.domain.model.ShowModel

const val STARTING_PAGE_INDEX = 1
const val NETWORK_PAGE_SIZE = 250

class ShowPagingSource(
    private val repository: ShowRepository,
) : PagingSource<Int, ShowModel>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, ShowModel> = try {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX
        val shows = repository.getShows(pageIndex)

        val nextKey = if (shows.isEmpty()) null
        else pageIndex + 1

        LoadResult.Page(
            data = shows,
            prevKey = if (pageIndex == STARTING_PAGE_INDEX) null else pageIndex,
            nextKey = nextKey
        )
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

    override fun getRefreshKey(state: PagingState<Int, ShowModel>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
}
