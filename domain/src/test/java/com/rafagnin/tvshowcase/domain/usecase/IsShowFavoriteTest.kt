package com.rafagnin.tvshowcase.domain.usecase

import com.rafagnin.tvshowcase.domain.data.ShowRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class IsShowFavoriteTest {

    @MockK lateinit var repository: ShowRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private fun make() = IsShowFavorite(repository)

    @Test
    fun `should usecase call repository properly`() = runBlocking {
        val favorite = true

        coEvery { repository.isShowFavorite(1) } returns favorite

        val result = make().invoke(1)
        assertEquals(result.data, favorite)
    }

    @Test
    fun `should usecase return repository error properly`() = runBlocking {
        val exception = Exception("message")

        coEvery { repository.isShowFavorite(1) } throws exception

        val result = make().invoke(1)
        assertEquals(result.data, false)
    }
}
