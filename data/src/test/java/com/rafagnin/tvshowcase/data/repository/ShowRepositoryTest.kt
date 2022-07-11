package com.rafagnin.tvshowcase.data.repository

import com.rafagnin.tvshowcase.data.local.LocalDataSource
import com.rafagnin.tvshowcase.data.mapper.EpisodeToDomainMapper
import com.rafagnin.tvshowcase.data.mapper.ShowToDomainMapper
import com.rafagnin.tvshowcase.data.model.EpisodeJson
import com.rafagnin.tvshowcase.data.model.LocalShowModel
import com.rafagnin.tvshowcase.data.model.SearchJson
import com.rafagnin.tvshowcase.data.model.ShowJson
import com.rafagnin.tvshowcase.data.remote.RemoteDataSource
import com.rafagnin.tvshowcase.domain.data.ShowRepository
import com.rafagnin.tvshowcase.domain.model.EpisodeModel
import com.rafagnin.tvshowcase.domain.model.ShowDetailModel
import com.rafagnin.tvshowcase.domain.model.ShowModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ShowRepositoryTest {

    @MockK lateinit var remoteDataSource: RemoteDataSource
    @MockK lateinit var localDataSource: LocalDataSource
    @MockK lateinit var showToDomainMapper: ShowToDomainMapper
    @MockK lateinit var episodeToDomainMapper: EpisodeToDomainMapper

    private lateinit var repository: ShowRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = ShowRepositoryImpl(
            remoteDataSource,
            localDataSource,
            showToDomainMapper,
            episodeToDomainMapper
        )
    }

    @Test
    fun `should getFavorites return expected flow`() = runBlocking {
        val mockLocalShows = listOf(mockk<LocalShowModel>())
        val mockShowModel = mockk<ShowModel>()

        coEvery { showToDomainMapper.map(mockLocalShows.first()) } returns mockShowModel
        coEvery { localDataSource.getFavorites() } returns flow { emit(mockLocalShows) }

        repository.getFavorites().collect {
            assertEquals(it, listOf(mockShowModel))
        }
    }

    @Test
    fun `should favoriteShow when boolean param is true`() = runBlocking {
        val mockShowDetailModel = mockk<ShowDetailModel>()
        val mockLocalShowModel = mockk<LocalShowModel>()

        coEvery { showToDomainMapper.map(mockShowDetailModel) } returns mockLocalShowModel
        coEvery { localDataSource.add(mockLocalShowModel) } just runs
        repository.favoriteShow(mockShowDetailModel, true)

        verify { localDataSource.add(mockLocalShowModel) }
    }

    @Test
    fun `should removeShow when boolean param is false`() = runBlocking {
        val mockShowDetailModel = mockk<ShowDetailModel>()
        val mockLocalShowModel = mockk<LocalShowModel>()

        coEvery { showToDomainMapper.map(mockShowDetailModel) } returns mockLocalShowModel
        coEvery { localDataSource.remove(mockLocalShowModel) } just runs
        repository.favoriteShow(mockShowDetailModel, false)

        verify { localDataSource.remove(mockLocalShowModel) }
    }

    @Test
    fun `should isShowFavorite return expected value`() = runBlocking {
        coEvery { localDataSource.exist(1) } returns true
        assertEquals(repository.isShowFavorite(1), true)
    }

    @Test
    fun `should searchShows return expected shows`() = runBlocking {
        val mockSearchJson = listOf(mockk<SearchJson>())
        val mockShowJson = mockk<ShowJson>()
        val mockShowModel = mockk<ShowModel>()

        coEvery { mockSearchJson.first().show } returns mockShowJson
        coEvery { showToDomainMapper.map(any<ShowJson>()) } returns mockShowModel
        coEvery { remoteDataSource.search("") } returns mockSearchJson

        assertEquals(repository.searchShows(""), listOf(mockShowModel))
    }

    @Test
    fun `should getShows return expected shows`() = runBlocking {
        val mockShowJson = listOf(mockk<ShowJson>())
        val mockShowModel = mockk<ShowModel>()

        coEvery { showToDomainMapper.map(any<ShowJson>()) } returns mockShowModel
        coEvery { remoteDataSource.getShows(1) } returns mockShowJson

        assertEquals(repository.getShows(1), listOf(mockShowModel))
    }

    @Test
    fun `should getShowDetail return expected show`() = runBlocking {
        val mockShowJson = mockk<ShowJson>()
        val mockShowModel = mockk<ShowDetailModel>()

        coEvery { showToDomainMapper.mapDetail(any()) } returns mockShowModel
        coEvery { remoteDataSource.getShowDetail(1) } returns mockShowJson

        assertEquals(repository.getShowDetail(1), mockShowModel)
    }

    @Test
    fun `should getSchedule return expected schedule`() = runBlocking {
        val mockShowJson = listOf(mockk<EpisodeJson>())
        val mockShowModel = mockk<EpisodeModel>()
        val date = "yyyy-mm-dd"

        coEvery { episodeToDomainMapper.map(any<EpisodeJson>()) } returns mockShowModel
        coEvery { remoteDataSource.getSchedule(date) } returns mockShowJson

        assertEquals(repository.getSchedule(date), listOf(mockShowModel))
    }
}
