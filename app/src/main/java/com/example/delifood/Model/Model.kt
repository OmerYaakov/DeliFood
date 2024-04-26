package com.example.delifood.Model

import android.util.Log
import java.util.Date

class Model private constructor(){

    val posts: MutableList<Post> = ArrayList()
    companion object {
        private val _instance: Model = Model()

        fun getInstance(): Model {
            return _instance
        }
    }

    init {
        for (i in 1..10) {
            val post = Post(
                "PostID: $i",
                "Post Image: $i",
                "Post Title: $i",
                "Post Description: $i hi ma kore korim li omer dfgdfgdfg dfg dfg dfgdfg dfg dfgdfgdfg dfgdf dfg dfg dfgd fg  ! ",
                "Post Publisher: $i",
                Date()
            )
            posts.add(post)
            Log.d("Model", "Post added: $i")
        }
    }

}