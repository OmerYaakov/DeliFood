package com.example.delifood.module.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.delifood.MainActivity
import com.example.delifood.Model.Model
import com.example.delifood.Model.Post
import com.example.delifood.R

class PostRecyclerViewFragment : Fragment() {
    private var posts: MutableList<Post>? = null
    private var homeNavBtn: Button? = MainActivity.homeNavBtn
    private var profileNavBtn: Button? = MainActivity.profileNavBtn
    private var addPostNavBtn: Button? = MainActivity.addPostNavBtn
    private var myPostsNavBtn: Button? = MainActivity.myPostsNavBtn


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_post_recycler_view, container, false)

        posts = Model.getInstance().posts
        val postRecyclerView = view.findViewById<RecyclerView>(R.id.postRecyclerView)
        postRecyclerView.setHasFixedSize(true)
        postRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        postRecyclerView.adapter = PostRecyclerViewAdapter(posts)
        addPostNavBtn?.visibility = View.VISIBLE
        profileNavBtn?.visibility = View.VISIBLE
        homeNavBtn?.visibility = View.VISIBLE
        myPostsNavBtn?.visibility = View.VISIBLE

        return view
    }
}
