package com.example.delifood.module.posts

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.delifood.data.Post
import com.example.delifood.R


class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var postTitle: TextView = itemView.findViewById(R.id.tvTitleRow)
    private var postDate: TextView = itemView.findViewById(R.id.tvPostRowDate)
    private var postDescription: TextView = itemView.findViewById(R.id.tvPostRowDescription)
    private var postPublisher: TextView = itemView.findViewById(R.id.tvPublisherRow)
    private var postImage: ImageView = itemView.findViewById(R.id.ivPostRow)


    fun bind(post: Post?) {
        postTitle.text = post?.title
        postDate.text = "Today"
        postDescription.text = "This is a description of the post."
        postPublisher.text = "Publisher Name"
        postImage.setImageResource(R.drawable.avatar_profile)

    }


}
