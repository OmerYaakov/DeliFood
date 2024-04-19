package com.example.delifood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.delifood.Model.Model
import com.example.delifood.Model.Post

class PostRecyclerViewActivity : AppCompatActivity() {
    var postRecyclerView: RecyclerView? = null
    var posts: MutableList<Post>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_recycler_view)

        posts = Model.getInstance().posts
        postRecyclerView = findViewById(R.id.postRecyclerView)
        postRecyclerView?.setHasFixedSize(true)


        postRecyclerView?.layoutManager = LinearLayoutManager(this)
        postRecyclerView?.adapter = PostRecyclerViewAdapter()
    }


   inner class PostViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        var postTitle: TextView? = null
        var postDate: TextView? = null
        var postDescription: TextView? = null
        var postPublisher: TextView? = null
        var postImage: ImageView? = null

        init {
            postTitle = itemView.findViewById(R.id.tvTitleRow)
            postDate = itemView.findViewById(R.id.tvPostRowDate)
            postDescription = itemView.findViewById(R.id.tvPostRowDescription)
            postPublisher = itemView.findViewById(R.id.tvPublisherRow)
            postImage = itemView.findViewById(R.id.ivPostRow)
        }

        fun bind(post: Post?){
            postTitle?.text = post?.postTitle
            postDate?.text = post?.postDate.toString()
            postDescription?.text = post?.postDescription
            postPublisher?.text = post?.postPublisher
            postImage?.setImageResource(R.drawable.avatar_profile)

        }
    }
    inner class PostRecyclerViewAdapter : RecyclerView.Adapter<PostViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.post_list_row, parent, false)
            return PostViewHolder(itemView)

        }

        override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
            val post = posts?.get(position)
            holder.bind(post)

        }

        override fun getItemCount(): Int {
            return posts?.size ?: 0
        }

    }
}