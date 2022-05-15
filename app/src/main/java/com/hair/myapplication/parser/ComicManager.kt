package com.hair.myapplication.parser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hair.myapplication.model.Comic
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.lang.IllegalArgumentException

object ComicManager {

    var comic = arrayListOf<Comic>()
    var allComic = arrayListOf<Comic>()
    var currentComic: Comic? = null
    private var currentPositions = 0
    private var allComics = MutableLiveData<ArrayList<Comic>>()
    val listComic: LiveData<ArrayList<Comic>>
        get() = allComics

    private fun getComic(): List<Comic>{
        val list = comic
        list?.forEach{
            allComic.addAll(listOf(it))
        }
        return allComic
    }
    fun getAllComic() {
        getComic()
        allComics.postValue(allComic)
    }


    fun setCurrentComic(c: Comic): Int{
        for (i in 0 until comic.size){
            if (comic[i] == c){
                currentComic = c
                return 1
            }
        }
        return -1
    }


}