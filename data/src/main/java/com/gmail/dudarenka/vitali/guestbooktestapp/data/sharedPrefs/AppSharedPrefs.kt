package com.gmail.dudarenka.vitali.guestbooktestapp.data.sharedPrefs

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class AppSharedPrefs(val context: Context) {
    private val sharedPrefs: SharedPreferences
    private var token: String = ""
    private var userId: String = ""
    private var isAdmin: Int = 0

    companion object {
        private const val SHARED_PREFS_NAME = "shrpprfnm"
        private const val KEY_TOKEN = "ktkn"
        private const val IS_ADMIN = "sdmn"
        private const val USER_ID = "usrid"
    }

    init {
        sharedPrefs = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun putToken(token: String) {
        this.token = token
        sharedPrefs.edit().putString(KEY_TOKEN, token).apply()
    }

    fun putUserId(userId: String) {
        this.userId = userId
        sharedPrefs.edit().putString(USER_ID, userId).apply()
    }

    fun getToken(): String {
        return sharedPrefs.getString(KEY_TOKEN, "")
    }

    fun getUserId(): String {
        return sharedPrefs.getString(USER_ID, "")
    }

    fun getIsAdmin(): Int {
        return sharedPrefs.getInt(IS_ADMIN, 0)
    }

    fun putIsAdmin(isAdmin: Int) {
        this.isAdmin = isAdmin
        sharedPrefs.edit().putInt(IS_ADMIN, isAdmin).apply()
    }


}