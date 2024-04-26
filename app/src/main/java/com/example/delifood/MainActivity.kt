package com.example.delifood

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.delifood.module.login.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Show the HomeFragment when MainActivity is created
        showHomeFragment()
    }

    private fun showHomeFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Replace the container with the HomeFragment
        fragmentTransaction.replace(R.id.fcvMainActivity, LoginFragment())

        // Commit the transaction
        fragmentTransaction.commit()
    }
}