package com.example.delifood.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.delifood.UserState
import com.example.delifood.data.User
import com.example.delifood.data.UserDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModel(
    private val dao: UserDao
) :ViewModel(){

    val state = MutableStateFlow(UserState())

    fun onEvent(event: UserEvent){
        when(event){
            is UserEvent.DeleteUser->{
                viewModelScope.launch {
                    dao.deleteUser(event.user)
                }
            }
            is UserEvent.SaveUser->{
                state.update { it.copy(
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
            is UserEvent.RegisterUser->{
                viewModelScope.launch {
                    val username = event.username
                    val email = event.email
                    val password = event.password

                    val user = User(
                        username = username,
                        email = email,
                        password = password
                    )
                    dao.upsertUser(user)
                }
            }
            else ->{
                TODO()
            }
        }
    }
}