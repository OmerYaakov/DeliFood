package com.example.delifood.Model

import java.util.Date

data class Post( val postID: String, val postImage: String, val postTitle: String, val postDescription: String, val postPublisher: String, val postDate: Date) {
    constructor() : this("", "", "", "", "", Date())
}