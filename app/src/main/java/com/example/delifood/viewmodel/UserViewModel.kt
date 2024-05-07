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
            is UserEvent.Register -> register(event.user)
            is UserEvent.Login -> login(event.uid)
            is UserEvent.Logout -> logout(event.user)
            is UserEvent.Update -> update(event.user)
            else -> Log.d("UserViewModel", "onEvent: Invalid event")
        }
    }
    private fun UserViewModel.register(user: User) {
        viewModelScope.launch {
            dao.upsertUser(user)
            state.update { it.copy(user = user) }
        }
    }

    private fun UserViewModel.login(uid : String) {
        viewModelScope.launch {
            Log.d("UserViewModel", "login: $uid")
            dao.getUserByUid(uid)
                .collect { dbUser ->
                    if (dbUser.uid.isNotEmpty()) {
                        Log.d("UserViewModel", "login success: ${dbUser.uid}")
                        state.update { it.copy(user = dbUser) }
                    } else {
                        Log.d("UserViewModel", "login failed")
                        state.update { it.copy(user = null) }
                    }
                }
        }
    }

    private fun UserViewModel.logout(user: User) {
        viewModelScope.launch {
            dao.deleteUser(user)
            state.update { it.copy(user = null) }
        }
    }

    private fun UserViewModel.update(user: User) {
        viewModelScope.launch {
            dao.upsertUser(user)
            state.update { it.copy(user = user) }
        }
    }

}
