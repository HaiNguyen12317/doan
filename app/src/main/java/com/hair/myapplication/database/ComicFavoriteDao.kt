package com.hair.myapplication.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hair.myapplication.model.ComicFavorite
import com.hair.myapplication.units.Const
import kotlinx.coroutines.flow.Flow


@Dao
interface ComicFavoriteDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComicFavorite(comicFavorite: ComicFavorite)

    @Query("SELECT * FROM ${Const.TABLE_NAME}")
    fun getAllComicFavorite(): Flow<List<ComicFavorite>>

    @Delete
    suspend fun deleteComicFavorite(comicFavorite: ComicFavorite)




}