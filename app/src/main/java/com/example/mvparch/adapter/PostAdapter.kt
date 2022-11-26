package com.example.mvparch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvparch.databinding.ItemLayoutBinding
import com.example.mvparch.model.Post

class PostAdapter: ListAdapter<Post, PostAdapter.PostViewHolder>(DiffCallBack()) {
    lateinit var onDeleteClick: (Post) -> Unit
    private class DiffCallBack : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PostViewHolder(private val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            with(binding) {
                textTitle.text = post.title
                textBody.text = post.body
            }
            itemView.setOnLongClickListener {
                onDeleteClick(post)
                true
            }
        }
    }
}