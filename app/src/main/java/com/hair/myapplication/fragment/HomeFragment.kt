package com.hair.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hair.myapplication.adapter.HomeAdapter
import com.hair.myapplication.databinding.FragmentHomeBinding
import com.hair.myapplication.model.Comic
import com.hair.myapplication.model.ComicFavorite
import com.hair.myapplication.units.OnClickItem
import com.hair.myapplication.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), OnClickItem {
    private lateinit var binding: FragmentHomeBinding

    private val vm: MainViewModel by viewModels()
    private var comicHeaderAdapter: HomeAdapter = HomeAdapter(this)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        setupRecyclerview()
        observeLiveData()
        return binding.root
    }

    private fun observeLiveData() {
        vm.listComic.observe(viewLifecycleOwner) {
            if (it!= null) {
                comicHeaderAdapter.differ.submitList(it)
            }

        }
    }
    private fun setupRecyclerview() {
        binding.rvHomeBody.apply {
            adapter = comicHeaderAdapter
            layoutManager =
                GridLayoutManager(requireContext(), 3)
        }
    }
    override fun onClickListener(comic: Comic) {
        vm.setCurrentComic(comic)
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
        findNavController().navigate(action)
    }

    override fun deleteComicFavorite(comicFavorite: ComicFavorite) {
        TODO("Not yet implemented")
    }

}