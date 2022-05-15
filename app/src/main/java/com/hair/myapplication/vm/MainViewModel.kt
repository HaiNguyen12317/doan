package com.hair.myapplication.vm

import android.app.Application
import android.text.Editable
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hair.myapplication.model.Comic
import com.hair.myapplication.model.ComicFavorite
import com.hair.myapplication.network.ComicClient
import com.hair.myapplication.network.ComicService
import com.hair.myapplication.parser.ComicManager
import com.hair.myapplication.parser.ComicManagerParser
import com.hair.myapplication.parser.ComicParse
import com.thaonx.mockt3h.repository.IItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.IllegalArgumentException
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val comicNetwork: ComicService,
    private val itemRepository: IItemRepository,
) : AndroidViewModel(application) {

    fun setCurrentComic(comic: Comic) {
//        _currentComic.postValue(comic)
        viewModelScope.launch {
            ComicManager.setCurrentComic(comic)
        }
    }

    private val _isActive = MutableLiveData<Boolean>()
    val isActive: LiveData<Boolean>
        get() = _isActive

    init {
        getAllComic()
        log()
    }

    private fun getComics(): LiveData<ArrayList<Comic>> = ComicManager.listComic
    val listComic: LiveData<ArrayList<Comic>>
        get() = getComics()


    private fun log() {
        Log.d("anhhai", "live data ${listComic.value?.size}")

    }

    private fun getAllComic() {

        viewModelScope.launch {

            var comics: Call<List<Comic>> = comicNetwork.getAllComic()
            comics.enqueue(object : Callback<List<Comic>> {

                override fun onResponse(call: Call<List<Comic>>, response: Response<List<Comic>>) {
                    if (response.isSuccessful) {
                        _isActive.postValue(true)
                        response.body().let {
                            if (it != null) {
                                ComicManager.comic = it as ArrayList<Comic>
                                ComicManager.getAllComic()
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<List<Comic>>, t: Throwable) {
                    Log.d("Error", "Call API error $t")
                    viewModelScope.launch {
                        comics = comicNetwork.getAllComic()
                    }
                }

            }
            )


        }
    }

    private val _comicFavorite: MutableLiveData<List<ComicFavorite>> = MutableLiveData()
    val comicFavorite
        get() = _comicFavorite

    fun insertFavComic(comic: ComicFavorite) {
        viewModelScope.launch {
            itemRepository.insertComicFavorite(comic)
        }
    }
    fun deleteComicFavorite(comic: ComicFavorite) {
        viewModelScope.launch {
            itemRepository.deleteComicFavorite(comic)
        }
    }

    fun getAllArticleFavorite() {
        viewModelScope.launch {
            itemRepository.getAllComicFavorite().catch {
                _comicFavorite.value = arrayListOf()
            }.collect { value ->
                _comicFavorite.value = value
            }
        }
    }



    fun sortComic(s: String) {
        s.uppercase()
        var k = 0
        for (i in 0 until ComicManager.allComic.size){
            val comic = ComicManager.allComic[i]
            val name = comic.name.uppercase()
            if (name.indexOf(s) >= 0){
                ComicManager.allComic[i] = ComicManager.allComic[k]
                ComicManager.allComic[k] = comic
                k++
            }
        }
    }


}

