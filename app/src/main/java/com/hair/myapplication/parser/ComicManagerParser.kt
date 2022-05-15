package com.hair.myapplication.parser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hair.myapplication.model.Comic

object ComicManagerParser {
    var comicParser = arrayListOf<Comic>()
    var allComicParser = arrayListOf<Comic>()
    var currentComicParser: Comic? = null
    private var allComicsParser = MutableLiveData<ArrayList<Comic>>()
    val listComicParser: LiveData<ArrayList<Comic>>
        get() = allComicsParser

    private fun getComic(): List<Comic>{
        val list = comicParser
        list?.forEach{
            allComicParser.addAll(listOf(it))
        }
        return allComicParser
    }
    fun getAllComic() {
        getComic()
        allComicsParser.postValue(allComicParser)
    }


    fun setCurrentComic(c: Comic): Int{
        for (i in 0 until comicParser.size){
            if (comicParser[i] == c){
                currentComicParser = c
                return 1
            }
        }
        return -1
    }
}