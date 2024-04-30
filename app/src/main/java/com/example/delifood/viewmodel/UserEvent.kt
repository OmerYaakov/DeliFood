package com.example.delifood.viewmodel

import com.example.delifood.data.User

sealed interface UserEvent {

    data class SaveUser(
        val username:String,
        val email: String,
        val password:String
    ):UserEvent

    data class DeleteUser(val user:User):UserEvent

}