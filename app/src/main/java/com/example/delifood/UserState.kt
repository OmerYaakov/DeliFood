package com.example.delifood

import com.example.delifood.data.User
import kotlinx.coroutines.flow.Flow

data class UserState(
    val user: User? = null,
)
