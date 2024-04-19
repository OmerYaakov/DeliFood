package com.example.delifood

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterPageActivity : AppCompatActivity() {
    var tvAppName: TextView? = null
    var etRegisterUsername: EditText? = null
    var etRegisterEmail: EditText? = null
    var etRegisterPassword: EditText? = null
    var imgProfile: ImageView? = null
    var btnUploadProfile: ImageButton? = null
    var btnRegister: Button? = null
    var btnCancel: Button? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)
        setupUI()
        }



    private fun setupUI(){
        // Reference to EditText fields
        etRegisterUsername = findViewById<EditText>(R.id.etRegisterUsername)
        etRegisterEmail = findViewById<EditText>(R.id.etRegisterEmail)
        etRegisterPassword = findViewById<EditText>(R.id.etRegisterPassword)
        imgProfile = findViewById<ImageView>(R.id.imgProfile)
        btnRegister = findViewById<Button>(R.id.btnRegister)
        btnUploadProfile = findViewById<ImageButton>(R.id.btnUploadProfile)
        tvAppName = findViewById<TextView>(R.id.tvAppName)
        btnCancel = findViewById<Button>(R.id.btnCancel)

        //register button
        btnRegister?.setOnClickListener {
            val registerUsername = etRegisterUsername?.text.toString()
            val registerEmail = etRegisterEmail?.text.toString()
            val registerPassword = etRegisterPassword?.text.toString()
            val registerImgProfile = imgProfile?.toString()
            val intent = Intent(this, PostRecyclerViewActivity::class.java)
            startActivity(intent)
        }
        btnCancel?.setOnClickListener(){
            finish()
        }
    }
}