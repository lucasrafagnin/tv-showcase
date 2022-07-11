package com.rafagnin.tvshowcase.domain.usecase

import com.rafagnin.tvshowcase.domain.Fixture
import com.rafagnin.tvshowcase.domain.Resource
import com.rafagnin.tvshowcase.domain.data.ShowRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetShowDetailTest {

    @MockK
    lateinit var repository: ShowRepository
    @MockK
    lateinit var isShowFavorite: IsShowFavorite

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private fun make() = GetShowDetail(repository, isShowFavorite)

    @Test
    fun `should usecase call repository properly`() = runBlocking {
        val mockShowDetailModel = Fixture.showDetailModel.copy(favorite = false)
        val id = 1L
        val favorite = true

        coEvery { repository.getShowDetail(id) } returns mockShowDetailModel
        coEvery { isShowFavorite.invoke(id) } returns Resource.Success(favorite)

        val result = make().invoke(id)
        assertEquals(result.data, mockShowDetailModel.copy(favorite = true))
    }

    @Test
    fun `should usecase return repository error properly`() = runBlocking {
        val exception = Exception("message")

        coEvery { repository.getShowDetail(any()) } throws exception

        val result = make().invoke(1)
        assert(result is Resource.Error)
        assertEquals(result.message, exception.message)
    }
}
