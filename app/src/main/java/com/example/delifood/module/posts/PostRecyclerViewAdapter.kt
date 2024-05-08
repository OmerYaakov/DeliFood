package com.example.delifood.module.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.delifood.PostState
import com.example.delifood.data.Post
import com.example.delifood.R
import kotlinx.coroutines.flow.MutableStateFlow

class PostRecyclerViewAdapter(
    private val postState: MutableStateFlow<PostState> = MutableStateFlow(PostState(emptyList()))
) : RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.post_list_row, parent, false)

        return PostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postState.value.posts[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return postState.value.posts.size?:0
    }
}
