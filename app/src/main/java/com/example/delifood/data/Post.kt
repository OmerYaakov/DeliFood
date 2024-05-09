package com.example.delifood.data
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    val title: String?,
    val uid: String?,
    val content: String?,
    val imageUrl: String?,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)