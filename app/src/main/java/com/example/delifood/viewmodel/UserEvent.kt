package com.example.delifood.viewmodel

import com.example.delifood.data.User

sealed interface UserEvent {

    data object SaveUser : UserEvent

    data class RegisterUser(
        val username: String,
        val email: String,
        val password: String
    ) : UserEvent

    data class SetUsername(val username:String):UserEvent
    data class SetEmail(val email:String):UserEvent
    data class SetPassword(val password:String):UserEvent

    data class DeleteUser(val user:User):UserEvent

    data class  LoginUser(val user: User):UserEvent


}