package com.gmail.dudarenka.vitali.guestbooktestapp.data.entity

import com.google.gson.annotations.SerializedName

data class FeedbackPageResponse(@SerializedName("data")
                                val data: List<FeedbackResponse>,
                                @SerializedName("current_page")
                                val page: Int) : DataEntity