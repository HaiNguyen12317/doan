package com.hair.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hair.myapplication.databinding.ItemHomeBinding
import com.hair.myapplication.fragment.SearchFragment
import com.hair.myapplication.model.Comic
import com.hair.myapplication.units.OnClickItem

class SearchAdapter(private val onComicItemClick: OnClickItem) :
RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private var binding: ItemHomeBinding? = null

    private val diffUtil = object : DiffUtil.ItemCallback<Comic>() {
        override fun areItemsTheSame(
            oldItem: Comic,
            newItem: Comic,
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: Comic,
            newItem: Comic,
        ): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class SearchViewHolder(private val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comic: Comic) {
            Glide.with(itemView.context).load(comic.image).into(binding.imgAvt)
            binding.tvTitle.text = comic.name
            binding.tvChap.text= comic.chap

            binding.root.setOnClickListener {
                binding.root.setOnClickListener(){
                    onComicItemClick.onClickListener(comic)
                }


            }
        }


    }
}