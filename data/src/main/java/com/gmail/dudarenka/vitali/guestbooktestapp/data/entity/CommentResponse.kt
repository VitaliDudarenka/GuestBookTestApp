package com.gmail.dudarenka.vitali.guestbooktestapp.data.entity

import com.google.gson.annotations.SerializedName

data class CommentResponse(@SerializedName("id")
                            val comment_id: String?,
                           @SerializedName("title")
                            val title: String?,
                           @SerializedName("message")
                            val message: String?,
                           @SerializedName("created_at")
                            val created_at: String?): DataEntity