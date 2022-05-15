package com.hair.myapplication.diff

import androidx.recyclerview.widget.DiffUtil
import com.hair.myapplication.model.Comic


object ComicDiff : DiffUtil.ItemCallback<Comic>() {
    override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean {
        return oldItem == newItem
    }
}