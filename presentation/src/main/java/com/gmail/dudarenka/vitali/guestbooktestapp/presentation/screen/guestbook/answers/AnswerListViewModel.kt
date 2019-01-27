package com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.answers

import android.databinding.ObservableBoolean
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.usecases.GetAnswersUseCase
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.usecases.IsAdminUseCase
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.app.App
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.base.BaseViewModel
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.GuestBookRouter
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class AnswerListViewModel : BaseViewModel<GuestBookRouter>() {
    var adapter: AnswerListAdapter? = AnswerListAdapter()
    val isProgressEnabled = ObservableBoolean(false)
    val isAdmin = ObservableBoolean(false)
    var token: String = ""
    var userId: String = ""
    var id: String = ""
    @Inject
    lateinit var getAnswersUseCase: GetAnswersUseCase
    @Inject
    lateinit var isAdminUseCase: IsAdminUseCase


    fun setCommentId(commentId: String) {
        App.appComponent.inject(this)
        val disposableAdmn = isAdminUseCase.isAdmin().subscribeBy(
                onSuccess = {
                    if (it != 0)
                        isAdmin.set(true)
                },
                onError = {
                    router!!.goToSartScreen()
                }
        )
        addToDisposable(disposableAdmn)
        id = commentId
        App.appComponent.inject(this)
        isProgressEnabled.set(true)
        val disposable = getAnswersUseCase.get(commentId).subscribeBy(
                onNext = {
                    adapter?.listData = it.toMutableList()
                    isProgressEnabled.set(false)
                },
                onError = {
                    isProgressEnabled.set(false)
                    router?.showError(it)
                }
        )
        addToDisposable(disposable)
    }

    fun onClickAdd() {
        router!!.goToAddAnswer(id)
    }

}