package com.hair.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hair.myapplication.databinding.ItemHomeBodyBinding
import com.hair.myapplication.fragment.HomeFragmentDirections
import com.hair.myapplication.model.Comic
import com.hair.myapplication.units.OnClickItem


class HomeBodyAdapter(private val comics : List<Comic>, private val onComicItemClick: OnClickItem): RecyclerView.Adapter<HomeBodyAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HomeViewHolder {
    return HomeViewHolder(ItemHomeBodyBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(comics[position])
    }

    override fun getItemCount(): Int = comics.size

    inner class HomeViewHolder(private val binding: ItemHomeBodyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comic : Comic) {

            Glide.with(binding.imgAvt)
                .load(comic.image)
                .centerCrop()
                .into(binding.imgAvt)
            binding.tvTitle.text = comic.name
            binding.tvChap.text = comic.chap

            binding.root.setOnClickListener(){

                onComicItemClick.onClickListener(comic)
            }
//            itemView.setOnClickListener {
//                OnClickItem()
//            }
        }
    }


}