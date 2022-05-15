package com.hair.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hair.myapplication.adapter.ComicFavoriteAdapter
import com.hair.myapplication.databinding.FragmentFavoriteBinding
import com.hair.myapplication.model.Comic
import com.hair.myapplication.model.ComicFavorite
import com.hair.myapplication.units.Const
import com.hair.myapplication.units.OnClickItem
import com.hair.myapplication.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment(),OnClickItem {
    private val vm: MainViewModel by viewModels()
    private lateinit var binding: FragmentFavoriteBinding
    private var articleFavoriteAdapter = ComicFavoriteAdapter(this)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater)

        obServerLivedata()
        setupRVFavorite()
        initData()
        return binding.root
    }

    private fun obServerLivedata() {
        vm.comicFavorite.observe(viewLifecycleOwner) { comicFavorites ->
            articleFavoriteAdapter.differ.submitList(comicFavorites)
            updateUi(comicFavorites)
        }
    }

    private fun initData() {
        vm.getAllArticleFavorite()
    }

    private fun updateUi(articlesFavorites: List<ComicFavorite>) {
        if (articlesFavorites.isEmpty()) {
            binding.apply {
                rvFavoriteNews.visibility = View.GONE
                layoutNotifyFavorite.visibility = View.VISIBLE
            }
        } else {
            binding.apply {
                rvFavoriteNews.visibility = View.VISIBLE
                layoutNotifyFavorite.visibility = View.GONE
            }
        }
    }

    private fun setupRVFavorite() {
        binding.rvFavoriteNews.apply {
            adapter = articleFavoriteAdapter
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onClickListener(comic: Comic) {
        vm.setCurrentComic(comic)
        val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment()
        findNavController().navigate(action)
    }

    override fun deleteComicFavorite(comicFavorite: ComicFavorite) {
        vm.deleteComicFavorite(comicFavorite)
        setupRVFavorite()
        Toast.makeText(requireActivity(), Const.NOTIFY_DIALOG, Toast.LENGTH_SHORT).show()
    }

}