package com.example.delifood.viewmodel


import PostEvent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.delifood.FirebaseAuthManager
import com.example.delifood.PostState
import com.example.delifood.SessionManager
import com.example.delifood.data.PostDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class PostViewModel(
    private val dao: PostDao
) :ViewModel(){

     val state = MutableStateFlow(PostState())

    fun onEvent(event: PostEvent){
        when(event){
            is PostEvent.CreatePost -> {
                viewModelScope.launch {
                    dao.upsertPost(event.post)
                    state.update { itState -> itState.copy(posts = itState.posts + event.post) }
                }
            }
            is PostEvent.DeletePost -> {
                viewModelScope.launch {
                    dao.deletePost(event.post)
                }
            }
            is PostEvent.GetAllPosts -> {
                viewModelScope.launch {
                    dao.getAllPosts().collect {
                        state.update { itState -> itState.copy(posts = it) }
                        Log.d("PostViewModel", "onEvent: ${state.value.posts}")
                    }
                }
            }
            is PostEvent.GetPostByUid -> {
                viewModelScope.launch {
                    dao.getPostByUid(event.uid).collect {
                        state.update { it.copy(posts = it.posts + it.posts) }
                    }
                }
            }
            is PostEvent.SetMyPosts -> {
                viewModelScope.launch {
                    SessionManager.getUserId().let { it ->
                        dao.getPostByUid(it).collect {itPost->
                            state.update { it.copy(posts = itPost) }
                        }
                    }
                }
            }

            else -> {
                TODO()
            }
        }
    }
}



