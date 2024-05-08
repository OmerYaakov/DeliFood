import com.example.delifood.data.Post

sealed interface PostEvent {
    data class CreatePost(val post: Post) : PostEvent
    data class DeletePost(val post: Post) : PostEvent
    data object GetAllPosts : PostEvent
    data class GetPostByUid(val uid: String) : PostEvent

    data object SetMyPosts : PostEvent

}