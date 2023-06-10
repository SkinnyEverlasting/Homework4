package com.example.homework4.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework4.Models.PostModel
import com.example.homework4.databinding.PostLayoutItemBinding

class PostAdapter: RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    private var postModels = listOf<PostModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostLayoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun getItemCount() = postModels.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.onBind(postModels[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(postModels: List<PostModel>) {
        this.postModels = postModels
        notifyDataSetChanged()
    }

    class PostViewHolder(private val binding: PostLayoutItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun onBind(postModel: PostModel) {
            binding.postInfoTextView.text = postModel.description
            Glide.with(binding.root).load(postModel.imageUrl).into(binding.postPictureView)
        }

    }
}