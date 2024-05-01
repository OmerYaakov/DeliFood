package com.example.delifood.module.register

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
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
import com.example.delifood.viewmodel.UserEvent

class RegisterFormFragment : Fragment() {


    private var tvAppName: TextView? = null
    private var etRegisterUsername: EditText? = null
    private var etRegisterEmail: EditText? = null
    private var etRegisterPassword: EditText? = null
    private var imgProfile: ImageView? = null
    private var btnUploadProfile: ImageButton? = null
    private var btnRegister: Button? = null
    private var btnCancel: Button? = null


    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_IMAGE_GALLERY = 2

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

        tvAppName = view.findViewById(R.id.tvAppName)
        etRegisterUsername = view.findViewById(R.id.etRegisterUsername)
        etRegisterEmail = view.findViewById(R.id.etRegisterEmail)
        etRegisterPassword = view.findViewById(R.id.etRegisterPassword)
        imgProfile = view.findViewById(R.id.imgProfile)
        btnRegister = view.findViewById(R.id.btnRegister)
        btnUploadProfile = view.findViewById(R.id.btnUploadProfile)
        btnCancel = view.findViewById(R.id.btnCancel)


        btnUploadProfile?.setOnClickListener {
            selectImage()
        }

        btnRegister?.setOnClickListener {

            val fragmentManager = requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            val homeFragment = PostRecyclerViewFragment()


            transaction.replace(R.id.fcvMainActivity, homeFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        btnCancel?.setOnClickListener {

            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun selectImage() {
        val options = arrayOf(
        "Take Photo", "Choose from Gallery", "Cancel")

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Choose your profile picture")

        builder.setItems(options) { dialog, which ->
            when (options[which]) {
                "Take Photo" -> dispatchTakePictureIntent()
                "Choose from Gallery" -> openGallery()
                "Cancel" -> dialog.dismiss()
            }
        }

        builder.show()
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_IMAGE_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    imgProfile?.setImageBitmap(imageBitmap)
                }
                REQUEST_IMAGE_GALLERY -> {
                    val imageUri = data?.data
                    imgProfile?.setImageURI(imageUri)
                }
            }
        }
    }
}

