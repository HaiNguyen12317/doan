package com.hair.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hair.myapplication.adapter.SearchAdapter
import com.hair.myapplication.databinding.FragmentSearchBinding
import com.hair.myapplication.model.Comic
import com.hair.myapplication.model.ComicFavorite
import com.hair.myapplication.units.OnClickItem
import com.hair.myapplication.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), OnClickItem {

    private lateinit var binding: FragmentSearchBinding
    private val vm: MainViewModel by viewModels()
    private var adapter: SearchAdapter= SearchAdapter(this)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater)
        binding.imgSearch.setOnClickListener {
            val s = binding.edtSearch.text
            vm.sortComic(s.toString())
            vm.listComic.observe(viewLifecycleOwner) {
                    if (it!= null) {
                        adapter.differ.submitList(it)
                    }

                }

        }
        return binding.root
    }

    override fun onClickListener(comic: Comic) {
        TODO("Not yet implemented")
    }

    override fun deleteComicFavorite(comicFavorite: ComicFavorite) {
        TODO("Not yet implemented")
    }

}