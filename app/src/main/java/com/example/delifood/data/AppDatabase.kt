package com.example.delifood.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Post::class, User::class],
    version = 1
)
abstract class AppDatabase:RoomDatabase(){
    abstract val postDao:PostDao
    abstract val userDao:UserDao
}

