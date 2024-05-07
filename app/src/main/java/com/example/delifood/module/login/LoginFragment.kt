package com.example.delifood.module.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.delifood.FirebaseAuthManager
import com.example.delifood.PostRecyclerViewFragment
import com.example.delifood.R
import com.example.delifood.UserState
import com.example.delifood.module.register.RegisterFormFragment
import com.example.delifood.viewmodel.UserEvent

class LoginFragment(
    private val state: UserState,
    private val onEvent: (UserEvent) -> Unit
):Fragment() {

    var firebaseAuthManager = FirebaseAuthManager()

    private var tvWelcome: TextView? = null
    private var editTextEmail: EditText? = null
    private var editTextPassword: EditText? = null
    private var btnLogin: Button? = null
    private var btnRegister: Button? = null

//    companion object {
//        fun newInstance(): LoginFragment {
//            return LoginFragment()
//        }
//    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_login, container, false)
        setupUI(view)
        return view
    }

    private fun setupUI(view: View?) {
        tvWelcome = view?.findViewById(R.id.tvWelcome)
        editTextEmail = view?.findViewById(R.id.editTextEmail)
        editTextPassword = view?.findViewById(R.id.editTextPassword)
        btnLogin = view?.findViewById(R.id.btnLogin)
        btnRegister = view?.findViewById(R.id.btnRegister)

        btnLogin?.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            val homeFragment = PostRecyclerViewFragment()

            val email = editTextEmail?.text.toString()
            val password = editTextPassword?.text.toString()
            firebaseAuthManager.loginUser(email, password) { result ->
                if (result.isSuccess) {
                    val user = result.getOrNull()
                    if (user != null) {
                        Log.d("LoginFragment", "User logged in")
                        transaction.replace(R.id.fcvMainActivity, homeFragment)
                        transaction.addToBackStack(null)
                        transaction.commit()
                    } else {
                        // Handle error
                    }
                }
            }

        }
        btnRegister?.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            val registerFragment = RegisterFormFragment(state = state, onEvent = onEvent)
            transaction.replace(R.id.fcvMainActivity, registerFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}