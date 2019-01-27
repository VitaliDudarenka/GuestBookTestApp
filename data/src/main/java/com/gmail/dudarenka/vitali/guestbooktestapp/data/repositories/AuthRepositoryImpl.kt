package com.gmail.dudarenka.vitali.guestbooktestapp.data.repositories

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.gmail.dudarenka.vitali.guestbooktestapp.data.entity.transformToData
import com.gmail.dudarenka.vitali.guestbooktestapp.data.net.RestService
import com.gmail.dudarenka.vitali.guestbooktestapp.data.sharedPrefs.AppSharedPrefs
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.entity.Login
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.entity.Registration
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.entity.TokenDomain
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.repositories.AuthRepository
import io.reactivex.Completable
import io.reactivex.Observable
import java.io.ByteArrayOutputStream


class AuthRepositoryImpl(private val sharedPrefs: AppSharedPrefs, private val apiService: RestService) : AuthRepository {

    override fun getToken(): TokenDomain {
        return TokenDomain(sharedPrefs.getToken(), sharedPrefs.getUserId())
    }

    override fun login(login: Login): Completable {
        return Completable.fromObservable(apiService
                .login(login.transformToData())
                .doOnNext {
                    sharedPrefs.putToken(it.api_token)
                    sharedPrefs.putToken(it.userId)
                })
    }

    override fun registration(registration: Registration): Completable {
        val COMPRESSION_QUALITY = 100
        val encodedImage: String
        val byteArrayBitmapStream = ByteArrayOutputStream()
        val bitmapPicture = BitmapFactory.decodeFile(registration.filePath)
        bitmapPicture.compress(Bitmap.CompressFormat.PNG, COMPRESSION_QUALITY,
                byteArrayBitmapStream)
        val b = byteArrayBitmapStream.toByteArray()
        encodedImage = Base64.encodeToString(b, Base64.DEFAULT)

        return Completable.fromObservable(apiService
                .registration(registration.transformToData(encodedImage))
                .doOnNext {
                    if (it != null)
                        sharedPrefs.putToken(it.api_token)
                })
    }

    override fun isLogin(): Boolean = sharedPrefs.getToken().isNotEmpty()
    override fun isAdmin(): Int = sharedPrefs.getIsAdmin()

}