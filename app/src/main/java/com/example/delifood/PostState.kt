package com.example.delifood

import com.example.delifood.Model.Post
import java.util.Date

data class PostState(
    val posts: List<Post> = emptyList()
)