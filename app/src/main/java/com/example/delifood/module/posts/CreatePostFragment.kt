package com.example.delifood.module.posts

import PostEvent
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.delifood.FileHandler
import com.example.delifood.PostState
import com.example.delifood.R
import com.example.delifood.SessionManager
import com.example.delifood.data.Post
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.ByteArrayOutputStream

class CreatePostFragment(
    private val state: MutableStateFlow<PostState>,
    private val onEvent: (PostEvent) -> Unit
) : Fragment() {

    private val REQUEST_PICK_IMAGE = 1
    private val REQUEST_TAKE_PHOTO = 2
    private var selectedPhotoUri: Uri? = null
    private var photoPreview: ImageView? = null
    private var selectPhotoButton: Button? = null
    private var submitPostButton: Button? = null
    private var postTitle: EditText? = null
    private var postContent: EditText? = null
    private  val fileHandler = FileHandler()




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_post, container, false)
        selectPhotoButton = view.findViewById(R.id.select_photo_button)
        submitPostButton = view.findViewById(R.id.submit_post_button)
        postTitle = view.findViewById(R.id.post_publisher)
        postContent = view.findViewById(R.id.post_content)
        photoPreview = view.findViewById(R.id.photo_preview)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectPhotoButton?.setOnClickListener {
            showPhotoOptionsDialog()
        }

        submitPostButton?.setOnClickListener {
            val title = postTitle?.text.toString()
            val description = postContent?.text.toString()
            val photo = selectedPhotoUri


            onEvent(PostEvent.CreatePost(Post(title,SessionManager.getUserId(),description,"/storage/emulated/0/Android/data/com.example.delifood/files/Pictures/${title}.png")))

            // TODO: Implement logic to upload the post with title, content, and photo

            // Reset the form
            postTitle?.setText("")
            postContent?.setText("")
            photoPreview?.setImageDrawable(null)
            selectedPhotoUri = null
        }
    }

    private fun showPhotoOptionsDialog() {
        val options = arrayOf("Take Photo", "Choose from Gallery", "Cancel")

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Select a photo")

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
        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_PICK_IMAGE)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_PICK_IMAGE -> {
                    selectedPhotoUri = data?.data
                    photoPreview?.setImageURI(selectedPhotoUri)
                    selectedPhotoUri?.let { uri ->
                        // Load the image from the URI
                        val bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
                        // Save the image to internal storage
                        fileHandler.saveImageToExternalStorage(requireContext(), bitmap,postTitle?.text.toString()+".png")
                    }
                }
                REQUEST_TAKE_PHOTO -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    // Save the image to internal storage
                    fileHandler.saveImageToExternalStorage(requireContext(), imageBitmap, postTitle?.text.toString()+".png")
                    // Display the image in the ImageView
                    photoPreview?.setImageBitmap(imageBitmap)
                }
            }
        }
    }

    private fun getImageUri(inContext: Context, inImage: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
        return Uri.parse(path)
    }
}
