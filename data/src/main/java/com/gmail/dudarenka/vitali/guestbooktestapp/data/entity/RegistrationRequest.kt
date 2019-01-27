package com.gmail.dudarenka.vitali.guestbooktestapp.data.entity

import com.google.gson.annotations.SerializedName


data class RegistrationRequest(@SerializedName("name")
                               val name: String,
                               @SerializedName("password")
                               val password: String,
                               @SerializedName("email")
                               val email: String,
                               @SerializedName("avatar")
                               val avatar: String) : DataEntity