package com.hair.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hair.myapplication.databinding.ItemFavoriteBinding
import com.hair.myapplication.fragment.FavoriteFragmentDirections
import com.hair.myapplication.model.Comic
import com.hair.myapplication.model.ComicFavorite
import com.hair.myapplication.parser.ComicManager
import com.hair.myapplication.units.OnClickItem
import com.hair.myapplication.vm.MainViewModel


class ComicFavoriteAdapter(private var clickItem: OnClickItem) :
    RecyclerView.Adapter<ComicFavoriteAdapter.ComicFavoriteViewHolder>() {

    private var binding: ItemFavoriteBinding? = null


    private val diffUtil = object : DiffUtil.ItemCallback<ComicFavorite>() {
        override fun areItemsTheSame(
            oldItem: ComicFavorite,
            newItem: ComicFavorite,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ComicFavorite,
            newItem: ComicFavorite,
        ): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicFavoriteViewHolder {
        binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComicFavoriteViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: ComicFavoriteViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ComicFavoriteViewHolder(private val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comicFavorite: ComicFavorite) {
            val comic = Comic(
                name= comicFavorite.name,
                image = comicFavorite.image,
                chap = comicFavorite.chap,
                link = comicFavorite.link
            )
            Glide.with(itemView.context).load(comicFavorite.image).into(binding.imgFavorite)
            binding.tvName.text = comicFavorite.name
            binding.tvChap.text= comicFavorite.chap
            binding.imgDelete.setOnClickListener {
                clickItem.deleteComicFavorite(comicFavorite)
            }
            binding.root.setOnClickListener {

                clickItem.onClickListener(comic)

            }
        }


    }
}
