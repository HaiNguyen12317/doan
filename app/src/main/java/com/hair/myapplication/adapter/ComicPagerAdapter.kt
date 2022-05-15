package com.hair.myapplication.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hair.myapplication.ComicActivity
import com.hair.myapplication.fragment.ChatFragment
import com.hair.myapplication.fragment.DetailFragment
import com.hair.myapplication.fragment.HomeFragment
import java.lang.IllegalArgumentException

class ComicPagerAdapter (private val comicActivity: ComicActivity) : FragmentStateAdapter(comicActivity) {
    private val tabs = arrayOf("Đề Xuất", "Chat Story")

    override fun getItemCount() = tabs.size
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> HomeFragment()
            1 -> ChatFragment()
            else -> throw IllegalArgumentException("Unknown fragment for position $position")
        }
    }
}