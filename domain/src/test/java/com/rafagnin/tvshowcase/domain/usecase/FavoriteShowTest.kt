package com.rafagnin.tvshowcase.domain.usecase

import com.rafagnin.tvshowcase.domain.Fixture
import com.rafagnin.tvshowcase.domain.Resource
import com.rafagnin.tvshowcase.domain.data.ShowRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FavoriteShowTest {

    @MockK lateinit var repository: ShowRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private fun make() = FavoriteShow(repository)

    @Test
    fun `should usecase call repository properly`() = runBlocking {
        val toFavorite = true
        val model = Fixture.showDetailModel.copy(favorite = !toFavorite)

        coEvery { repository.favoriteShow(model, toFavorite) } just runs

        val result = make().invoke(model, toFavorite)
        assertEquals(result.data, model.copy(favorite = toFavorite))
    }

    @Test
    fun `should usecase return repository error properly`() = runBlocking {
        val exception = Exception("message")

        coEvery { repository.favoriteShow(any(), true) } throws exception

        val result = make().invoke(mockk(), true)
        assert(result is Resource.Error)
        assertEquals(result.message, exception.message)
    }
}
