package com.gmail.dudarenka.vitali.guestbooktestapp.data.entity

import com.gmail.dudarenka.vitali.guestbooktestapp.domain.entity.*


fun Login.transformToData(): LoginRequest {
    return LoginRequest(email = email, password = password)
}

fun Registration.transformToData(encodedImage: String): RegistrationRequest {
    return RegistrationRequest(name = name, password = password, email = email, avatar = encodedImage)
}

fun FeedbackResponse.transformToDomain(): Feedback {
    return Feedback(comment_id = comment_id, title = title, message = message,
            created_at = created_at, user = userResponse.transformToDomain())
}
fun CommentResponse.transformToDomain(): Feedback {
    return Feedback(comment_id = comment_id, title = title, message = message,
            created_at = created_at, user = null)
}
fun Feedback.transformToData(token: String): FeedbackRequest {
    return FeedbackRequest(title = title, message = message, token = token)
}


fun UserResponse.transformToDomain(): User {
    return User(name = name, id = id, is_admin = is_admin)
}


