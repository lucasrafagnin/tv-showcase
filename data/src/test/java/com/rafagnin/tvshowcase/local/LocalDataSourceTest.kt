package com.rafagnin.tvshowcase.local

import com.rafagnin.tvshowcase.data.local.LocalDataSource
import com.rafagnin.tvshowcase.data.local.dao.UserShowDao
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class LocalDataSourceTest {

    @MockK lateinit var dao: FavoriteDao
    @MockK lateinit var showDao: UserShowDao

    private lateinit var localDataSource: LocalDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        localDataSource = LocalDataSource(dao, showDao)
    }

    @Test
    fun `should getFavorites call expected dao`() = runBlocking {
        coEvery { dao.getAll() } returns emptyFlow()
        localDataSource.getFavorites()
        verify { dao.getAll() }
    }

    @Test
    fun `should exist call expected dao`() = runBlocking {
        coEvery { dao.exist(1) } returns true
        localDataSource.isFavorite(1)
        verify { dao.exist(1) }
    }

    @Test
    fun `should add call expected dao`() = runBlocking {
        val model = mockk<LocalFavoriteModel>()
        coEvery { dao.insert(model) } just runs
        localDataSource.addFavorite(model)
        verify { dao.insert(model) }
    }

    @Test
    fun `should remove call expected dao`() = runBlocking {
        val model = mockk<LocalFavoriteModel>()
        coEvery { dao.delete(model) } just runs
        localDataSource.removeFavorite(model)
        verify { dao.delete(model) }
    }
}
