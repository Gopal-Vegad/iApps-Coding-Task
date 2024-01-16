package com.iapps.practical.ui.adapters

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iapps.practical.databinding.ItemLayoutBinding
import com.iapps.practical.model.FeedModel
import com.iapps.practical.utils.exts.openLink


class DataAdapter : ListAdapter<FeedModel, DataAdapter.DataViewHolder>(Companion) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutBinding.inflate(layoutInflater)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind()
    }

    inner class DataViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = getItem(adapterPosition)

            Glide.with(binding.root).load(item.media).into(binding.ivImage)

            binding.tvDescription.text = Html.fromHtml(item.description, Html.FROM_HTML_MODE_COMPACT);

            binding.cvMain.setOnClickListener {
                it.context.openLink(item.link)
            }
        }
    }

    companion object : DiffUtil.ItemCallback<FeedModel>() {
        override fun areItemsTheSame(oldItem: FeedModel, newItem: FeedModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FeedModel, newItem: FeedModel): Boolean {
            return oldItem.id == newItem.id
        }

    }

}