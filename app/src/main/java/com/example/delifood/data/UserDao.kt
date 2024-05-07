package com.example.delifood.data

import androidx.room.Dao
import androidx.room.Upsert
import androidx.room.Delete
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Upsert
    suspend fun upsertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM user WHERE uid = :uid")
    fun getUserByUid(uid: String): Flow<User>

    @Query("SELECT * FROM user WHERE id = :id")
    fun getCurrentUser(id:Int): Flow<User>

    @Upsert
    suspend fun setCurrentUser(user: User){
        upsertUser(user)
    }
}