package com.example.delifood.module.userProfile

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.delifood.R
import com.example.delifood.SessionManager
import com.example.delifood.UserState
import com.example.delifood.module.login.LoginFragment
import com.example.delifood.viewmodel.UserEvent
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.ByteArrayOutputStream


class MyProfileFragment(
    private val state: MutableStateFlow<UserState>,
    private val onEvent: (UserEvent) -> Unit
) : Fragment() {



   private var usernameValueTextView: TextView? = null
   private var emailValueTextView: TextView? = null
   private var editUsernameEditText: EditText? = null
    private var passwordValueTextView: TextView? = null
   private var profilePhoto: ImageView? = null
   private var saveButton: Button? = null
   private var editButton: Button? = null
   private var cancelButton: Button? = null
    private var logoutButton: Button? = null
   private var isEditing = false
   private var editPhotoProfile: ImageButton? = null
   private var username: String? = null
   private var email: String? = null
    private var password: String? = null

    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_IMAGE_GALLERY = 2
    private var originalPhotoUri: Uri? = null
    private var newPhotoUri: Uri? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_profile, container, false)
        setupUI(view)
        editButton?.setOnClickListener {
            setEditingMode()
        }

       return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up user profile data
        Log.d("MyProfileFragment", "Setting up user profile data")
        Log.d("MyProfileFragment", "User: ${state.value.user}")
        val username = state.value.user?.username ?: "N/A"
        val email = state.value.user?.email ?: "N/A"
        val uid = state.value.user?.uid ?: "N/A"

        usernameValueTextView?.text = username
        emailValueTextView?.text = email
        passwordValueTextView?.text = uid

//        originalPhotoUri = profilePhoto?.drawable?.toBitmap()?.let { bitmap ->
//            val uri = bitmapToUri(requireContext(), bitmap)
//            uri
//        }
    }

   private fun setupUI(view: View) {
        usernameValueTextView = view.findViewById(R.id.usernameValueTextView)
        emailValueTextView = view.findViewById(R.id.emailValueTextView)
        editUsernameEditText = view.findViewById(R.id.editUsernameValueTextView)
        saveButton = view.findViewById(R.id.saveProfileButton)
        editButton = view.findViewById(R.id.editProfileButton)
       logoutButton = view.findViewById(R.id.logoutProfileButton)
        cancelButton = view.findViewById(R.id.CancelProfileButton)
        profilePhoto = view.findViewById(R.id.profilePictureImageView)
        editPhotoProfile = view.findViewById(R.id.btnUploadProfile)
        passwordValueTextView = view.findViewById(R.id.uidValueTextView)

       logoutButton?.setOnClickListener {
           val fragmentManager = requireActivity().supportFragmentManager
           val transaction = fragmentManager.beginTransaction()
           val loginFragment = LoginFragment(state = state, onEvent = onEvent)

           Log.d("MyProfileFragment", "Logging out user")
           SessionManager.logoutUser()

           transaction.replace(R.id.fcvMainActivity, loginFragment)
           transaction.addToBackStack(null)
           transaction.commit()

       }

    }

    private fun setEditingMode() {
        isEditing = true
        editUsernameEditText?.visibility = View.VISIBLE
        editPhotoProfile?.visibility = View.VISIBLE
        saveButton?.visibility = View.VISIBLE
        cancelButton?.visibility = View.VISIBLE
        editButton?.visibility = View.GONE
        usernameValueTextView?.visibility = View.GONE
        logoutButton?.visibility = View.GONE
        editPhotoProfile?.setOnClickListener() {
            selectImage()
        }
        cancelButton?.setOnClickListener {
            setViewMode()
        }
        saveButton?.setOnClickListener {
            saveProfile()
            setViewMode()
        }
        originalPhotoUri?.let { profilePhoto?.setImageURI(it) }

    }

    private fun setViewMode() {
        isEditing = false
        editUsernameEditText?.visibility = View.GONE
        editPhotoProfile?.visibility = View.GONE
        saveButton?.visibility = View.GONE
        cancelButton?.visibility = View.GONE
        editButton?.visibility = View.VISIBLE
        usernameValueTextView?.visibility = View.VISIBLE
        logoutButton?.visibility = View.VISIBLE
        newPhotoUri?.let {
            profilePhoto?.setImageURI(it)
        } ?: run {
            // If no new photo was selected, reset to the original photo
            originalPhotoUri?.let { profilePhoto?.setImageURI(it) }
        }


        logoutButton?.setOnClickListener {
            Log.d("MyProfileFragment", "Logging out user")
            Log.d("MyProfileFragment", "Logging out user")
        }
    }

    private fun saveProfile() {
         username = editUsernameEditText?.text.toString()

        usernameValueTextView?.text = username
        emailValueTextView?.text = email
        passwordValueTextView?.text = password
        Log.d("saveProfile", "Saving profile {username: $username, email: $email}")
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
                    profilePhoto?.setImageBitmap(imageBitmap)
                }
                REQUEST_IMAGE_GALLERY -> {
                    val imageUri = data?.data
                    profilePhoto?.setImageURI(imageUri)
                    newPhotoUri = imageUri
                }
            }
        }
    }

    private fun bitmapToUri(context: Context, bitmap: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, "Title", null)
        return Uri.parse(path)
    }

}