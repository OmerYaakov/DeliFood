package com.example.delifood.viewmodel

import com.example.delifood.data.User

sealed interface UserEvent {

    data class Register(val user: User) : UserEvent

    data class Login(val uid: String) : UserEvent

    data class Logout(val user: User) : UserEvent

    data class Update(val user: User) : UserEvent

}