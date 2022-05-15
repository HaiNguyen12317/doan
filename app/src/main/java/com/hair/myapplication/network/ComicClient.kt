package com.hair.myapplication.network

import com.hair.myapplication.units.Const
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ComicClient {
       private var httpClient: OkHttpClient = OkHttpClient.Builder()
        .callTimeout(10, TimeUnit.MINUTES)
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(Const.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    operator fun invoke(): ComicService = retrofit.create(ComicService::class.java)

}