package com.example.delifood.module.posts

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.delifood.R
import com.example.delifood.data.Post
import java.io.File
import com.squareup.picasso.Picasso



class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var postTitle: TextView = itemView.findViewById(R.id.tvTitleRow)
    private var postDescription: TextView = itemView.findViewById(R.id.tvPostRowDescription)
    private var postPublisher: TextView = itemView.findViewById(R.id.tvPublisherRow)
    private var postImage: ImageView = itemView.findViewById(R.id.ivPostRow)

    fun bind(post: Post?) {
        val imageFile = File(post?.imageUrl?:"")
        postTitle.text = post?.title
        postDescription.text = post?.content
        postPublisher.text = "Publisher Name"
        Picasso.get().load(imageFile).into(postImage)

    }
}
