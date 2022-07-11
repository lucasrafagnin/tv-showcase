package com.rafagnin.tvshowcase.domain.usecase

import com.rafagnin.tvshowcase.domain.Resource
import com.rafagnin.tvshowcase.domain.data.ShowRepository
import com.rafagnin.tvshowcase.domain.model.ShowModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetFavoritesTest {

    @MockK lateinit var repository: ShowRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private fun make() = GetFavorites(repository)

    @Test
    fun `should usecase call repository properly`() = runBlocking {
        val mockShowModels = listOf(mockk<ShowModel>())

        coEvery { repository.getFavorites() } returns flow { emit(mockShowModels) }

        make()
            .invoke()
            .collectLatest {
                assertEquals(it.data, mockShowModels)
            }
    }

    @Test
    fun `should usecase return repository error properly`() = runBlocking {
        coEvery { repository.getFavorites() } returns flow { throw Exception() }

        make()
            .invoke()
            .collectLatest {
                assert(it is Resource.Error)
            }
    }
}
