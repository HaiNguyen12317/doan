package com.hair.myapplication.units
import com.hair.myapplication.model.Comic
import com.hair.myapplication.model.ComicFavorite

interface OnClickItem {
    fun onClickListener(comic: Comic)
    fun deleteComicFavorite(comicFavorite: ComicFavorite)
}