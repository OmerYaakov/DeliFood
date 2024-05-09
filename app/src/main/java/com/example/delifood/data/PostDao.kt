package com.example.delifood.data


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Upsert
    suspend fun upsertPost(post: Post)

    @Delete
    fun deletePost(post: Post)

    @Query("SELECT * FROM post ORDER BY id DESC")
    fun getAllPosts(): Flow<List<Post>>

    @Query("SELECT * FROM post WHERE uid = :uid")
    fun getPostByUid(uid: String): Flow<List<Post>>

}