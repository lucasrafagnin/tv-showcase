package com.rafagnin.tvshowcase.domain.usecase

import com.rafagnin.tvshowcase.domain.Resource
import com.rafagnin.tvshowcase.domain.data.ShowRepository
import com.rafagnin.tvshowcase.domain.model.ShowModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SearchShowTest {

    @MockK lateinit var repository: ShowRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private fun make() = SearchShow(repository)

    @Test
    fun `should usecase call repository properly`() = runBlocking {
        val mockShowModels = listOf(mockk<ShowModel>())

        coEvery { repository.searchShows("") } returns mockShowModels

        val result = make().invoke("")
        assertEquals(result.data, mockShowModels)
    }

    @Test
    fun `should usecase return repository error properly`() = runBlocking {
        val exception = Exception("message")

        coEvery { repository.searchShows("") } throws exception

        val result = make().invoke("")
        assert(result is Resource.Error)
        assertEquals(result.message, exception.message)
    }
}
