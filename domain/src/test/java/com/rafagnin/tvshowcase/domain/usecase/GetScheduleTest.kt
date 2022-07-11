package com.rafagnin.tvshowcase.domain.usecase

import com.rafagnin.tvshowcase.domain.Resource
import com.rafagnin.tvshowcase.domain.data.ShowRepository
import com.rafagnin.tvshowcase.domain.model.EpisodeModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetScheduleTest {

    @MockK lateinit var repository: ShowRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private fun make() = GetSchedule(repository)

    @Test
    fun `should usecase call repository properly`() = runBlocking {
        val mockEpisodeModels = listOf(mockk<EpisodeModel>())

        coEvery { repository.getSchedule(any()) } returns mockEpisodeModels

        val result = make().invoke()
        assertEquals(result.data, mockEpisodeModels)
    }

    @Test
    fun `should usecase return repository error properly`() = runBlocking {
        val exception = Exception("message")

        coEvery { repository.getSchedule(any()) } throws exception

        val result = make().invoke()
        assert(result is Resource.Error)
        assertEquals(result.message, exception.message)
    }
}
