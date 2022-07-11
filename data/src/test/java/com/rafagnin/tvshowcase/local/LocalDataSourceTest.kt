package com.rafagnin.tvshowcase.local

import com.rafagnin.tvshowcase.data.local.LocalDataSource
import com.rafagnin.tvshowcase.data.local.ShowDao
import com.rafagnin.tvshowcase.data.model.LocalShowModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class LocalDataSourceTest {

    @MockK lateinit var dao: ShowDao

    private lateinit var localDataSource: LocalDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        localDataSource = LocalDataSource(dao)
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
        localDataSource.exist(1)
        verify { dao.exist(1) }
    }

    @Test
    fun `should add call expected dao`() = runBlocking {
        val model = mockk<LocalShowModel>()
        coEvery { dao.insert(model) } just runs
        localDataSource.add(model)
        verify { dao.insert(model) }
    }

    @Test
    fun `should remove call expected dao`() = runBlocking {
        val model = mockk<LocalShowModel>()
        coEvery { dao.delete(model) } just runs
        localDataSource.remove(model)
        verify { dao.delete(model) }
    }
}
