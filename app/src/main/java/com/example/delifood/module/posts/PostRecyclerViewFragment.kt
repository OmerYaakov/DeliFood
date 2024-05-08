package com.example.delifood.module.posts

import PostEvent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.delifood.MainActivity
import com.example.delifood.PostState
import com.example.delifood.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PostRecyclerViewFragment(
    private val postState: MutableStateFlow<PostState> =MutableStateFlow(PostState(emptyList())),
    private val onEvent: (PostEvent) -> Unit = {}
) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_post_recycler_view, container, false)

        viewLifecycleOwner.lifecycleScope.launch {
            onEvent(PostEvent.GetAllPosts)
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                postState.collect {
                    Log.d("PostRecyclerViewFragment", "onCreateView: ${it.posts}")
                    val postRecyclerView = view.findViewById<RecyclerView>(R.id.postRecyclerView)
                    postRecyclerView.setHasFixedSize(true)
                    postRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                    postRecyclerView.adapter = PostRecyclerViewAdapter(postState)
                }
            }
        }

        var myPosts: Button = view.findViewById(R.id.myPostsBtn)
        myPosts.setOnClickListener {
            onEvent(PostEvent.SetMyPosts)
            Log.d("PostRecyclerViewFragment", "clicked myPosts")
        }

        var allPosts: Button =view.findViewById(R.id.allPostsBtn)
        allPosts.setOnClickListener {
            onEvent(PostEvent.GetAllPosts)
            Log.d("PostRecyclerViewFragment", "clicked allPosts")
        }


        // Other UI setup...
        MainActivity.homeNavBtn?.visibility = View.VISIBLE
        MainActivity.weatherNavBtn?.visibility = View.VISIBLE
        MainActivity.profileNavBtn?.visibility = View.VISIBLE
        MainActivity.addPostNavBtn?.visibility = View.VISIBLE

        return view
    }
}
