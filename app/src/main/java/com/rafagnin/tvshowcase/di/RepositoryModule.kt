package com.rafagnin.tvshowcase.di

import com.rafagnin.tvshowcase.data.local.LocalDataSource
import com.rafagnin.tvshowcase.data.mapper.EpisodeToDomainMapper
import com.rafagnin.tvshowcase.data.mapper.ShowToDomainMapper
import com.rafagnin.tvshowcase.data.remote.RemoteDataSource
import com.rafagnin.tvshowcase.data.repository.ShowRepositoryImpl
import com.rafagnin.tvshowcase.domain.data.ShowRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideShowRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        showToDomainMapper: ShowToDomainMapper,
        episodeToDomainMapper: EpisodeToDomainMapper,
    ): ShowRepository = ShowRepositoryImpl(
        remoteDataSource = remoteDataSource,
        localDataSource = localDataSource,
        showToDomainMapper = showToDomainMapper,
        episodeToDomainMapper = episodeToDomainMapper
    )
}
