package com.example.delifood.viewmodel


import android.util.MutableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.delifood.UserState
import com.example.delifood.data.UserDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class UserViewModel(
    private val dao: UserDao
) :ViewModel(){

    private val _state = MutableStateFlow(UserState())

    fun onEvent(event: UserEvent){
        when(event){
            is UserEvent.DeleteUser->{
                viewModelScope.launch {
                    dao.deleteUser(event.user)
                }
            }
            is UserEvent.SaveUser->{
                _state.update { it.copy(
                ) }
            }
            is UserEvent.SetEmail->{
                TODO()
            }
            is UserEvent.SetPassword->{
                TODO()
            }
            is UserEvent.SetUsername->{
                TODO()
            }
            else ->{
                TODO()
            }
        }
    }
}