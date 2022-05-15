package com.hair.myapplication.parser


import com.hair.myapplication.model.Comic
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.lang.IllegalArgumentException

object ComicParse  {
    fun getAllComic(): List<Comic>{
        val comics = arrayListOf<Comic>()
        try {

//        val link = "view-source:https://www.nettruyenmoi.com/"
            val link = "https://www.nettruyenonline.com/"
            val document: Document = Jsoup.connect(link).get()

            val comicElements = document.getElementsByClass("item")
            for (i in 0 until comicElements.size) {
                val comicElement: Element = comicElements[i]
                val url = comicElement.getElementsByTag("a").attr("href")
                val name = comicElement.getElementsByTag("a").attr("title")
                val img = comicElement.getElementsByTag("img").attr("src")
                val chapElement: Elements = comicElement.getElementsByClass("slide-caption")

                comics.add(Comic(name,img,url))
            }
        }catch (e: Exception){
            IllegalArgumentException("aaabab $e")
        }


        return comics
    }

//    fun getChap(position: Int): List<Chapter>{
//        val comic = getAllComic()
//        val link = comic[position].link.toString()
//        val chaps = arrayListOf<Chapter>()
//        val document: Document = Jsoup.connect(link).get()
//        val chapElements = document.getElementsByClass("col-xs-9 chapter")
//        for (i in 0 until chapElements.size){
//            val chapElement = chapElements[i]
//            val chap = chapElement.getElementsByTag("a").text().uppercase()
//            val linkChap = chapElement.getElementsByTag("a").attr("href")
//            chaps.add(Chapter(chap,linkChap))
//        }
//        return chaps
//    }
//    fun getImg(link : String) : List<Chap>{
//        link
//        Log.d("Anhhai", "link chap $link")
//        val imgs = arrayListOf<Chap>()
//        val document: Document = Jsoup.connect(link).get()
//        val chapElements = document.getElementsByTag("li")
//        for (i in 0 until chapElements.size){
//            val chapElement = chapElements[i]
//            val linkImg = chapElement.getElementsByTag("img").attr("src")
//            imgs.add(Chap(linkImg))
//        }
//        return imgs
//    }
}