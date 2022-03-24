fun main() {
    print("Hello")
}


object WallService {
    private var posts = emptyArray<Post>()

    fun add(post: Post): Post {
        if (!posts.isEmpty()) {
            val oldId = posts.last().id
            val postCopy = post.copy(id = oldId + 1);
            posts += postCopy
        } else {
            posts += post.copy(1)
        }
        return posts.last()
    }

    fun update(post: Post): Boolean {
        val id = post.id
        for ((index, oldPost) in posts.withIndex()) {
            if (id == oldPost.id) {
                val tempPost = oldPost
                posts[index] = post.copy(date = oldPost.date);
                return true
            }
        }
        return false
    }
}


data class Post(
    val id: Int = 0,
    val ownerId: Int,
    val fromId: Int,
    val createdBy: Int,
    val date: Int,
    val text: String,
    val replyOwnerId: Int,
    val replyPostId: Int,
    val friendsOnly: Boolean,
    val comments: Comments,
    val copyright: Copyright,
    val likes: Likes,
    val reposts: Reposts,
    val view: View,
    val postType: String,
    val signerId: Int,
    val canPin: Boolean,
    val canDelete: Boolean,
    val canEdit: Boolean,
    val isPinned: Boolean,
    val markedAsAds: Boolean,
    val isFavorite: Boolean,
    val donut: Donut,
    val postponedId: Int,

    val postSource: Object?,
    val geo: Geo,
    val copyHistory: Array<Object>?,

    val attachments: Array<Attachment>


) {
    data class Comments(
        val count: Int,
        val canPost: Boolean,
        val groupsCanPost: Boolean,
        val canClose: Boolean,
        val canOpen: Boolean
    )

    data class Copyright(
        val id: Int,
        val link: String,
        val name: String,
        val type: String
    )

    data class Likes(
        val count: Int,
        val userLikes: Boolean,
        val canLike: Boolean,
        val canPublish: Boolean
    )

    data class Reposts(
        val count: Int,
        val userReposted: Boolean
    )

    data class View(
        val count: Int
    )

    data class Donut(
        val isDonut: Boolean,
        val paidDuration: Int,
        val placeholder: Placeholder,
        val canPublishFreeCopy: Boolean,
        val editMode: String
    ) {
        class Placeholder(
            val text: String
        )
    }

    data class Geo(
        val type: String,
        val coordinates: String,
        val place: Object?
    )

    interface Attachment {
        val id: Int
        val albumId: Int
        val ownerId: Int
        val userId: Int

        fun infoAsFun() = "ID: $id, album: $albumId, owner: $ownerId, user: $userId"
    }

    class VideoAttachment(
        override val id: Int,
        override val albumId: Int,
        override val ownerId: Int,
        override val userId: Int,
        val filmName: String,
        val director: String,
        val year: Int
    ) : Attachment {
        override fun infoAsFun(): String {
            return super.infoAsFun() + ", film: $filmName, director: $director, year: $year"
        }
    }

    class AudioAttachment(
        override val id: Int,
        override val albumId: Int,
        override val ownerId: Int,
        override val userId: Int,
        val songName: String,
        val singer: String,
        val composer: String,
        val poet: String
    ) : Attachment {
        override fun infoAsFun(): String {
            return super.infoAsFun() + ", song: $songName, singer: $singer, composer: $composer, poet: $poet"
        }
    }

    class PhotoAttachment(
        override val id: Int,
        override val albumId: Int,
        override val ownerId: Int,
        override val userId: Int
    ) : Attachment

    class JournalAttachment(
        override val id: Int,
        override val albumId: Int,
        override val ownerId: Int,
        override val userId: Int,
        val journalName: String,
        val date: String
    ) : Attachment {
        override fun infoAsFun(): String {
            return super.infoAsFun() + ", journal: $journalName, date: $date"
        }
    }

    class BookAttachment(
        override val id: Int,
        override val albumId: Int,
        override val ownerId: Int,
        override val userId: Int,
        val author: String,
        val bookName: String,
    ) : Attachment {
        override fun infoAsFun(): String {
            return super.infoAsFun() + ", author: $author, book: $bookName"
        }
    }
}