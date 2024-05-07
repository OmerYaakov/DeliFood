package com.example.delifood

import com.example.delifood.module.posts.CreatePostFragment
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.delifood.data.AppDatabase
import com.example.delifood.module.login.LoginFragment
import com.example.delifood.module.posts.PostRecyclerViewFragment
import com.example.delifood.module.userProfile.MyProfileFragment
import com.example.delifood.viewmodel.PostViewModel
import com.example.delifood.viewmodel.UserEvent


import com.example.delifood.viewmodel.UserViewModel


class MainActivity : AppCompatActivity() {

    private val db by lazy {
    Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "app.db"
    ).build()
    }

    private val postViewModel by viewModels<PostViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return PostViewModel(db.postDao) as T
                }
            }
        }
    )
    private val userViewModel by viewModels<UserViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return UserViewModel(db.userDao) as T
                }
            }
        }
    )


    companion object {
        var homeNavBtn: Button? = null
        var profileNavBtn: Button? = null
        var addPostNavBtn: Button? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val postState = postViewModel.state.value // get the current state
        val userState = userViewModel.state.value


        val loginFragment = LoginFragment(state = userState, onEvent = userViewModel::onEvent)
        val createPostFragment = CreatePostFragment( state = postState, onEvent = postViewModel::onEvent);
        val postRecyclerViewFragment = PostRecyclerViewFragment();
        val myProfileFragment = MyProfileFragment(state = userState, onEvent = userViewModel::onEvent)

        setContentView(R.layout.activity_main)

        addPostNavBtn = findViewById(R.id.addPostNevBtn)
        profileNavBtn = findViewById(R.id.myProfileNavBtn)
        homeNavBtn = findViewById(R.id.homeNavBtn)

        // init session manager
        SessionManager.init(this)

        if (SessionManager.isLoggedIn()) {
//            userViewModel.onEvent(UserEvent.Login(SessionManager.getUserId()))
            showHomeFragment(postRecyclerViewFragment)
            addPostNavBtn?.visibility = View.VISIBLE
            profileNavBtn?.visibility = View.VISIBLE
            homeNavBtn?.visibility = View.VISIBLE
        } else {
            showLoginFragment(loginFragment)
        }



        addPostNavBtn = findViewById(R.id.addPostNevBtn)
        homeNavBtn = findViewById(R.id.homeNavBtn)
        profileNavBtn = findViewById(R.id.myProfileNavBtn)

        addPostNavBtn?.setOnClickListener {
            showNewPostFragment(createPostFragment)
        }

        homeNavBtn?.setOnClickListener {
            showHomeFragment(postRecyclerViewFragment)
        }

        profileNavBtn?.setOnClickListener {
            showMyProfileFragment(myProfileFragment)
        }
    }

//    private fun removeFragment() {
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//
//        fragmentTransaction.replace(R.id.fcvMainActivity, LoginFragment)
//
//        fragmentTransaction.commit()
//    }

    private fun showNewPostFragment( createPostFragment: CreatePostFragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fcvMainActivity, createPostFragment)
        fragmentTransaction.commit()
    }

    private fun showLoginFragment( loginFragment: LoginFragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fcvMainActivity, loginFragment)
        fragmentTransaction.commit()
    }
    private fun showHomeFragment( postRecyclerViewFragment: PostRecyclerViewFragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()


        fragmentTransaction.replace(R.id.fcvMainActivity, postRecyclerViewFragment)
        fragmentTransaction.commit()
    }

    private fun showMyProfileFragment( myProfileFragment: MyProfileFragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fcvMainActivity, myProfileFragment)
        fragmentTransaction.commit()
    }
}