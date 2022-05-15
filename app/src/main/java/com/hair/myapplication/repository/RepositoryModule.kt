package com.hair.myapplication.repository


import com.hair.myapplication.database.ComicFavoriteDao
import com.hair.myapplication.database.ComicFavoriteDatabase
import com.thaonx.mockt3h.repository.IItemRepository
import com.thaonx.mockt3h.repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideItemRepository(
        comicFavoriteDao: ComicFavoriteDao,
    ): IItemRepository {
        return ItemRepository(comicFavoriteDao)
    }
}