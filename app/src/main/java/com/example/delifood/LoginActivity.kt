package com.example.delifood

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val loginButton = findViewById<Button>(R.id.btnLogin)
        val registerButton = findViewById<Button>(R.id.btnRegister)
        loginButton.setOnClickListener(::onLoginButtonClick)
        registerButton.setOnClickListener(::onRegisterButtonClick)
    }

    fun onLoginButtonClick (view: View) {
        val intent = Intent(this, PostRecyclerViewActivity::class.java)
        startActivity(intent)
    }
    fun onRegisterButtonClick (view: View) {
        val intent = Intent(this, RegisterPageActivity::class.java)
        startActivity(intent)
    }
}


