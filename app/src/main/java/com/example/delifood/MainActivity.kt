package com.example.delifood

import CreatePostFragment
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.delifood.module.login.LoginFragment
import com.example.delifood.module.register.RegisterFormFragment
import com.example.delifood.module.userProfile.MyProfileFragment

class MainActivity : AppCompatActivity() {

   private val loginFragment = LoginFragment();
   private val createPostFragment = CreatePostFragment();
   private val postRecyclerViewFragment = PostRecyclerViewFragment();
    private val myProfileFragment = MyProfileFragment();
    companion object {
        var homeNavBtn: Button? = null
        var profileNavBtn: Button? = null
        var addPostNavBtn: Button? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addPostNavBtn = findViewById(R.id.addPostNevBtn)
        profileNavBtn = findViewById(R.id.myProfileNavBtn)
        homeNavBtn = findViewById(R.id.homeNavBtn)

        showLoginFragment()
        addPostNavBtn = findViewById(R.id.addPostNevBtn)
        homeNavBtn = findViewById(R.id.homeNavBtn)
        profileNavBtn = findViewById(R.id.myProfileNavBtn)

        addPostNavBtn?.setOnClickListener {
            showNewPostFragment()
        }

        homeNavBtn?.setOnClickListener {
            showHomeFragment()
        }

        profileNavBtn?.setOnClickListener {
            showMyProfileFragment()
        }
    }

    private fun removeFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fcvMainActivity, LoginFragment())

        fragmentTransaction.commit()
    }

    private fun showNewPostFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fcvMainActivity, createPostFragment)
        fragmentTransaction.commit()
    }

    private fun showLoginFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fcvMainActivity, loginFragment)
        fragmentTransaction.commit()
    }
    private fun showHomeFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fcvMainActivity, postRecyclerViewFragment)
        fragmentTransaction.commit()
    }

    private fun showMyProfileFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fcvMainActivity, myProfileFragment)
        fragmentTransaction.commit()
    }
}