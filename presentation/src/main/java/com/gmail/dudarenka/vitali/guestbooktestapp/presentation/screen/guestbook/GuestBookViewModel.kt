package com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook

import android.databinding.ObservableField
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.usecases.GetTokenUseCase
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.usecases.IsLoginUseCase
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.app.App
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.base.BaseViewModel
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject


class GuestBookViewModel : BaseViewModel<GuestBookRouter>() {
    private var isLogin: Boolean = false

    @Inject
    lateinit var isLoginUseCase: IsLoginUseCase


    init {

    }

    fun isLogin(): Boolean {
        App.appComponent.inject(this)
        val disposable = isLoginUseCase.isLogin().subscribeBy(
                onSuccess = {
                    isLogin = it

                },
                onError = {
                    router!!.goToSartScreen()
                }
        )
        addToDisposable(disposable)
        return isLogin
    }
}