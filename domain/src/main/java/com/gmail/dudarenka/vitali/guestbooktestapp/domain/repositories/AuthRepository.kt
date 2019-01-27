package com.gmail.dudarenka.vitali.guestbooktestapp.domain.repositories

import android.media.session.MediaSession
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.entity.Login
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.entity.Registration
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.entity.TokenDomain
import io.reactivex.Completable
import io.reactivex.Observable

interface AuthRepository : BaseRepository {

    fun login(login: Login): Completable
    fun registration(registration: Registration): Completable
    fun isLogin(): Boolean
    fun isAdmin(): Int
    fun getToken(): TokenDomain

}