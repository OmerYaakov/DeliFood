package com.example.delifood

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.delifood.Model.Post


class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var postTitle: TextView = itemView.findViewById(R.id.tvTitleRow)
    private var postDate: TextView = itemView.findViewById(R.id.tvPostRowDate)
    private var postDescription: TextView = itemView.findViewById(R.id.tvPostRowDescription)
    private var postPublisher: TextView = itemView.findViewById(R.id.tvPublisherRow)
    private var postImage: ImageView = itemView.findViewById(R.id.ivPostRow)


    fun bind(post: Post?) {
        postTitle.text = post?.postTitle
        postDate.text = post?.postDate.toString()
        postDescription.text = post?.postDescription
        postPublisher.text = post?.postPublisher
        postImage.setImageResource(R.drawable.avatar_profile)

    }


}
