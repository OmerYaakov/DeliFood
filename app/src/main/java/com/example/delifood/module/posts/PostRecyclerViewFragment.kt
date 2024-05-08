package com.example.delifood.module.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.delifood.MainActivity
import com.example.delifood.PostState
import com.example.delifood.R
import kotlinx.coroutines.flow.MutableStateFlow

class PostRecyclerViewFragment(
    private val postState: MutableStateFlow<PostState> =MutableStateFlow(PostState(emptyList()))
) : Fragment() {

    // Other code...

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_post_recycler_view, container, false)

        val postRecyclerView = view.findViewById<RecyclerView>(R.id.postRecyclerView)
        postRecyclerView.setHasFixedSize(true)
        postRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        postRecyclerView.adapter = PostRecyclerViewAdapter(postState)

        // Other UI setup...
        MainActivity.homeNavBtn?.visibility = View.VISIBLE
        MainActivity.weatherNavBtn?.visibility = View.VISIBLE
        MainActivity.profileNavBtn?.visibility = View.VISIBLE
        MainActivity.addPostNavBtn?.visibility = View.VISIBLE

        return view
    }
}
