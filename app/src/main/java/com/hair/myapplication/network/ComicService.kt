package com.hair.myapplication.network

import com.hair.myapplication.model.Comic
import retrofit2.Call
import retrofit2.http.GET

interface ComicService {

    @GET("9bd24c6d0c3d18acb626")
    fun getAllComic(): Call<List<Comic>>

}