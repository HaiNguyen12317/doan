package com.thaonx.mockt3h.repository

import com.hair.myapplication.database.ComicFavoriteDao
import com.hair.myapplication.database.ComicFavoriteDatabase
import com.hair.myapplication.model.ComicFavorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ItemRepository @Inject constructor(
    private val comicFavoriteDao: ComicFavoriteDao,
) : IItemRepository {


    //database

    override suspend fun insertComicFavorite(comicFavorite: ComicFavorite) {
        return withContext(Dispatchers.IO) {
            comicFavoriteDao.insertComicFavorite(comicFavorite)
        }
    }

    override fun getAllComicFavorite(): Flow<List<ComicFavorite>> {
        return comicFavoriteDao.getAllComicFavorite().flowOn(Dispatchers.IO)
    }

    override suspend fun deleteComicFavorite(comicFavorite: ComicFavorite) {
        return withContext(Dispatchers.IO) {
            comicFavoriteDao.deleteComicFavorite(comicFavorite)
        }
    }
}