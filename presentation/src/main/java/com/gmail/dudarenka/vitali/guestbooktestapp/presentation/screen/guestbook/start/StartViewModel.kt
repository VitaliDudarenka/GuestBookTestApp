package com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.start

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.entity.Login
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.usecases.IsLoginUseCase
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.usecases.LoginUseCase
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.app.App
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.base.BaseViewModel
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.GuestBookRouter
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject


class StartViewModel : BaseViewModel<GuestBookRouter>() {
    val password = ObservableField<String>("")
    val email = ObservableField<String>("")
    val isProgressEnabled = ObservableBoolean(false)
    @Inject
    lateinit var loginUseCase: LoginUseCase


    fun onLoginClick() {
        if (!checkRegForm()) {
            router?.showSaveError()
            return
        }
        val login = Login(password = password.get()!!, email = email.get()!!)
        App.appComponent.inject(this)
        isProgressEnabled.set(true)
        val disposable = loginUseCase.login(login).subscribeBy(
                onComplete = {
                    router!!.goToFeedbackList()
                    isProgressEnabled.set(false)
                },
                onError = {
                    isProgressEnabled.set(false)
                    router?.showError(it)
                }
        )
        addToDisposable(disposable)
    }

    fun onRegistrationClick() {
        router!!.goToRegistration()
    }

    private fun checkRegForm(): Boolean {
        return !(email.get()!!.isEmpty() || password.get()!!.isEmpty())
    }
}
