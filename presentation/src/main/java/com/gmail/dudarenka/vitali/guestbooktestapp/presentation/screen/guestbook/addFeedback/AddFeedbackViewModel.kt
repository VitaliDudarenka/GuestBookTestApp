package com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.addFeedback

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.entity.Feedback
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.usecases.AddFeedbackUseCase
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.app.App
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.base.BaseViewModel
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.GuestBookRouter
import io.reactivex.rxkotlin.subscribeBy
import java.net.URISyntaxException
import javax.inject.Inject


class AddFeedbackViewModel : BaseViewModel<GuestBookRouter>() {
    val title = ObservableField<String>("")
    val message = ObservableField<String>("")
    val isProgressEnabled = ObservableBoolean(false)
    private var mSocket: Socket? = null
    @Inject
    lateinit var addFeedbackUseCase: AddFeedbackUseCase

    init {
        try {
            mSocket = IO.socket("http://pusher.cpl.by:6020")
        } catch (e: URISyntaxException) {
        }

    }
    fun onSaveClick() {
        if (!checkForm()) {
            router?.showSaveError()
            return
        }
        val feedback = Feedback(title = title.get()!!, message = message.get()!!, user = null, created_at = "", comment_id = "")
        App.appComponent.inject(this)
        isProgressEnabled.set(true)
        val disposable = addFeedbackUseCase.addFeedback(feedback).subscribeBy(
                onComplete = {
                    mSocket!!.connect()
                    mSocket!!.emit("public-push", message)
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

    private fun checkForm(): Boolean {
        return !(title.get()!!.isEmpty() || message.get()!!.isEmpty())
    }
}
