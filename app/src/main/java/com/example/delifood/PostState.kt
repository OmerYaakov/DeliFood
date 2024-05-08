package com.example.delifood

import com.example.delifood.data.Post

data class PostState(
    val posts: List<Post> = emptyList()
)