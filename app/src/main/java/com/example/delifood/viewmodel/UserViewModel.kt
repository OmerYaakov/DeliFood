package com.example.delifood.viewmodel


import android.util.MutableBoolean
import androidx.lifecycle.ViewModel
import com.example.delifood.data.UserDao


class UserViewModel(
    private val dao: UserDao
) :ViewModel(){

    private val isLoggedIn = MutableBoolean(false)

    fun onEvent(event: UserEvent){
        when(event){
            is UserEvent.DeleteUser->{
                TODO()
            }
            is UserEvent.SaveUser->{
                TODO()
            }
        }
    }
}