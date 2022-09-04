package com.rafagnin.tvshowcase.di

import android.content.Context
import androidx.room.Room
import com.rafagnin.tvshowcase.data.local.AppDatabase
import com.rafagnin.tvshowcase.data.local.dao.FavoriteDao
import com.rafagnin.tvshowcase.data.local.dao.ShowDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "TvShowcase"
        ).build()
    }

    @Provides
    fun provideFavoritesDao(appDatabase: AppDatabase): FavoriteDao {
        return appDatabase.favoriteDao()
    }

    @Provides
    fun provideShowsDao(appDatabase: AppDatabase): ShowDao {
        return appDatabase.showDao()
    }
}
