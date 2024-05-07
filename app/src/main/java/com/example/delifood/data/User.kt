package com.example.delifood.data

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(

    val username: String,
    val email: String,
    val uid : String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

)

