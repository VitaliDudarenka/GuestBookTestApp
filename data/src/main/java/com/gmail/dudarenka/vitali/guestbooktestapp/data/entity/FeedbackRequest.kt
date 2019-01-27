package com.gmail.dudarenka.vitali.guestbooktestapp.data.entity

import com.google.gson.annotations.SerializedName

data class FeedbackRequest(@SerializedName("title")
                            val title: String?,
                           @SerializedName("message")
                            val message: String?,
                           @SerializedName("api_token")
                            val token: String?) : DataEntity