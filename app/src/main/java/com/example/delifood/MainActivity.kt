package com.example.delifood

import CreatePostFragment
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.delifood.data.AppDatabase
import com.example.delifood.module.login.LoginFragment
import com.example.delifood.module.register.RegisterFormFragment
import com.example.delifood.module.userProfile.MyProfileFragment


import com.example.delifood.data.User
import com.example.delifood.viewmodel.UserViewModel


class MainActivity : AppCompatActivity() {

    private val db by lazy {
    Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "user.db"
    ).build()
    }

    private val viewModel by viewModels<UserViewModel>(
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

        val state = viewModel.state.value   // get the current state

        val loginFragment = LoginFragment(state = state, onEvent = viewModel::onEvent)
        val createPostFragment = CreatePostFragment();
        val postRecyclerViewFragment = PostRecyclerViewFragment();
        val myProfileFragment = MyProfileFragment();

        setContentView(R.layout.activity_main)

        addPostNavBtn = findViewById(R.id.addPostNevBtn)
        profileNavBtn = findViewById(R.id.myProfileNavBtn)
        homeNavBtn = findViewById(R.id.homeNavBtn)

        showLoginFragment( loginFragment)

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