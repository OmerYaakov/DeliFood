import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.delifood.R

class CreatePostFragment : Fragment() {

    private val REQUEST_PICK_IMAGE = 1
    private var selectedPhotoUri: Uri? = null
    var photo_preview: ImageView? = null
    var select_photo_button: Button? = null
    var submit_post_button: Button? = null
    var post_title: EditText? = null
    var post_content: EditText? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        select_photo_button = view?.findViewById(R.id.select_photo_button)
        submit_post_button = view?.findViewById(R.id.submit_post_button)
        post_title = view?.findViewById(R.id.post_title)
        post_content = view?.findViewById(R.id.post_content)
        photo_preview = view?.findViewById(R.id.photo_preview)
        return inflater.inflate(R.layout.fragment_create_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        select_photo_button?.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_PICK_IMAGE)
        }

        submit_post_button?.setOnClickListener {
            val title = post_title?.text.toString()
            val content = post_content?.text.toString()
            val photo = selectedPhotoUri

            // TODO: Implement logic to upload the post with title, content, and photo

            // Reset the form
            post_title?.setText("")
            post_content?.setText("")
            photo_preview?.setImageDrawable(null)
            selectedPhotoUri = null
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            selectedPhotoUri = data.data
            photo_preview?.setImageURI(selectedPhotoUri)
        }
    }
}