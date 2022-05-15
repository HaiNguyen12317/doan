package com.hair.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hair.myapplication.model.Comic
import com.hair.myapplication.model.ComicFavorite
import com.hair.myapplication.units.Const

@Database(entities = [ComicFavorite::class],exportSchema = false,version = 1)
abstract class ComicFavoriteDatabase: RoomDatabase() {
    abstract fun getComicFavoriteDao(): ComicFavoriteDao
    companion object {

        @Volatile
        private var INSTANCE: ComicFavoriteDatabase? = null

        fun getInstance(context: Context): ComicFavoriteDatabase = INSTANCE ?: synchronized(this) {

            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                ComicFavoriteDatabase::class.java,
                Const.DATABASE_NAME).build()
                .also {
                    INSTANCE = it
                }
        }
    }

}