package com.example.delifood.module.userProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.delifood.R

class MyProfileFragment : Fragment() {
        var usernameValueTextView: TextView? = null
        var emailValueTextView: TextView? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up user profile data
        val username = "JohnDoe"
        val email = "john.doe@example.com"

        usernameValueTextView?.text = username
        emailValueTextView?.text = email

    }
}