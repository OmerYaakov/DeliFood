package com.example.delifood.module.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.delifood.PostRecyclerViewFragment
import com.example.delifood.R

class RegisterFormFragment : Fragment() {

    // Reference to EditText fields
    private var tvAppName: TextView? = null
    private var etRegisterUsername: EditText? = null
    private var etRegisterEmail: EditText? = null
    private var etRegisterPassword: EditText? = null
    private var imgProfile: ImageView? = null
    private var btnUploadProfile: ImageButton? = null
    private var btnRegister: Button? = null
    private var btnCancel: Button? = null

    companion object {
        fun newInstance(): RegisterFormFragment {
            return RegisterFormFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_register_page, container, false)
        setupUI(view)
        return view
    }

    private fun setupUI(view: View) {
        // Reference to EditText fields
        tvAppName = view.findViewById(R.id.tvAppName)
        etRegisterUsername = view.findViewById(R.id.etRegisterUsername)
        etRegisterEmail = view.findViewById(R.id.etRegisterEmail)
        etRegisterPassword = view.findViewById(R.id.etRegisterPassword)
        imgProfile = view.findViewById(R.id.imgProfile)
        btnRegister = view.findViewById(R.id.btnRegister)
        btnUploadProfile = view.findViewById(R.id.btnUploadProfile)
        btnCancel = view.findViewById(R.id.btnCancel)

        // Register button click listener
        btnRegister?.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            val homeFragment = PostRecyclerViewFragment()
            transaction.replace(R.id.fcvMainActivity, homeFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        // Cancel button click listener
        btnCancel?.setOnClickListener {
            // Handle cancel button click here
            // Example: pop back stack to return to previous fragment
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}
