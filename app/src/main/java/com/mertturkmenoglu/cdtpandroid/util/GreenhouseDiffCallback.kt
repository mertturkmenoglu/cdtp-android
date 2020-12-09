package com.mertturkmenoglu.cdtpandroid.util

import androidx.recyclerview.widget.DiffUtil
import com.mertturkmenoglu.cdtpandroid.data.model.Greenhouse

object GreenhouseDiffCallback {
    val callback = object : DiffUtil.ItemCallback<Greenhouse>() {
        override fun areItemsTheSame(oldItem: Greenhouse, newItem: Greenhouse): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Greenhouse, newItem: Greenhouse): Boolean {
            return oldItem == newItem
        }
    }
}