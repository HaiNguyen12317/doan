package com.hair.myapplication.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hair.myapplication.databinding.ItemHomeBinding
import com.hair.myapplication.model.Comic
import com.hair.myapplication.units.OnClickItem


class HomeAdapter(private val onComicItemClick: OnClickItem)  : RecyclerView.Adapter<HomeAdapter.ItemHomeViewHolder>() {

    private var binding: ItemHomeBinding? = null

    private val differUtils = object : DiffUtil.ItemCallback<Comic>() {
        override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean {
            return oldItem.name== newItem.name
        }

        override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, differUtils)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HomeAdapter.ItemHomeViewHolder {
        binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHomeViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ItemHomeViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ItemHomeViewHolder(private val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comic: Comic) {

            Glide.with(binding.imgAvt)
                .load(comic.image)
                .centerCrop()
                .into(binding.imgAvt)
            binding.tvTitle.text = comic.name
            binding.tvChap.text = comic.chap
            binding.root.setOnClickListener(){
                onComicItemClick.onClickListener(comic)
            }
//            itemView.setOnClickListener { mView ->
//                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(comic)
//                mView.findNavController().navigate(action)
//            }
        }
    }
}