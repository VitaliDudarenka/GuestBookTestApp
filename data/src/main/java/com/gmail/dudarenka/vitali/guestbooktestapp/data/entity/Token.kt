package com.gmail.dudarenka.vitali.guestbooktestapp.data.entity

import com.google.gson.annotations.SerializedName


data class Token(@SerializedName("api_token") val api_token: String,
                 @SerializedName("id") val userId: String) : DataEntity