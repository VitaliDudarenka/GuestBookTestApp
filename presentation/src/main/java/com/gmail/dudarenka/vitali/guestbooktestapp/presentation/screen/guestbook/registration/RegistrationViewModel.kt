package com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.registration

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.entity.Registration
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.usecases.RegistrationUseCase
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.app.App
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.base.BaseViewModel
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.GuestBookRouter
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject


class RegistrationViewModel : BaseViewModel<GuestBookRouter>() {
    val name = ObservableField<String>("")
    val password = ObservableField<String>("")
    val password2 = ObservableField<String>("")
    val email = ObservableField<String>("")
    val imagePath = ObservableField<String>("")
    val isProgressEnabled = ObservableBoolean(false)

    @Inject
    lateinit var registrationUseCase: RegistrationUseCase

    fun onClickSave() {
        if (!checkRegForm()) {
            router?.showSaveError()
            return
        }
        if (!checkPassword()) {
            router?.showPasswordError()
            return
        }
        val registration = Registration(name = name.get()!!, password = password.get()!!, email = email.get()!!, filePath = imagePath.get()!!)
        App.appComponent.inject(this)
        isProgressEnabled.set(true)
        val disposable = registrationUseCase.registration(registration).subscribeBy(
                onComplete = {
                    router!!.showRegComplete()
                    isProgressEnabled.set(false)
                },
                onError = {
                    isProgressEnabled.set(false)
                    router?.showError(it)
                }
        )
        addToDisposable(disposable)
    }

    private fun checkRegForm(): Boolean {
        return !(name.get()!!.isEmpty() || password.get()!!.isEmpty() || password2.get()!!.isEmpty() || email.get()!!.isEmpty())
    }


    private fun checkPassword(): Boolean {
        return password.get() == password2.get()
    }

}
