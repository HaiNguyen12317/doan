package com.hair.myapplication.database

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun providerDatabase(application: Application): ComicFavoriteDatabase =
        ComicFavoriteDatabase.getInstance(application)

    @Singleton
    @Provides
    fun providerComicFavoriteDao(database: ComicFavoriteDatabase) =
        database.getComicFavoriteDao()
}