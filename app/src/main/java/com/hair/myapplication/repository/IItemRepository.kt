package com.thaonx.mockt3h.repository

import com.hair.myapplication.model.ComicFavorite
import kotlinx.coroutines.flow.Flow

interface IItemRepository {



    suspend fun insertComicFavorite(comicFavorite: ComicFavorite)

    fun getAllComicFavorite(): Flow<List<ComicFavorite>>

    suspend fun deleteComicFavorite(comicFavorite: ComicFavorite)
}