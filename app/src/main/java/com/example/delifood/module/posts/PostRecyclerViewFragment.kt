package com.example.delifood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.delifood.Model.Model
import com.example.delifood.Model.Post

class PostRecyclerViewFragment : Fragment() {
    private var posts: MutableList<Post>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_post_recycler_view, container, false)

        posts = Model.getInstance().posts
        val postRecyclerView = view.findViewById<RecyclerView>(R.id.postRecyclerView)
        postRecyclerView.setHasFixedSize(true)
        postRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        postRecyclerView.adapter = PostRecyclerViewAdapter(posts)

        return view
    }
}
