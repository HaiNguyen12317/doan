package com.hair.myapplication.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.hair.myapplication.databinding.FragmentDetailBinding
import com.hair.myapplication.model.Comic
import com.hair.myapplication.model.ComicFavorite
import com.hair.myapplication.parser.ComicManager
import com.hair.myapplication.units.Const
import com.hair.myapplication.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val vm: MainViewModel by viewModels()

    //    private val args: DetailFragmentArgs by navArgs()
    private lateinit var currentComic: Comic
    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)
//        currentComic = args.comics!!
        currentComic = ComicManager.currentComic!!
        setupWebView()
        binding.btnAdd.setOnClickListener {
            addFavoriteArticle(requireView())
        }
    }

    private fun addFavoriteArticle(view: View) {
        val favComic = ComicFavorite(
            name = currentComic.name,
            link = currentComic.link,
            chap = currentComic.chap,
            image = currentComic.image,
        )

        vm.insertFavComic(favComic)
        Snackbar.make(view, Const.NOTIFY, Snackbar.LENGTH_SHORT).show()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        binding.webView.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true

            loadUrl(currentComic.link)

        }
    }
}
