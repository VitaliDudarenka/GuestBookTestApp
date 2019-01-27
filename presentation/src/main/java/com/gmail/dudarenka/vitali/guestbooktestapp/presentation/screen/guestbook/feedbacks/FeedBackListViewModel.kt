package com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.feedbacks

import android.databinding.ObservableBoolean
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.entity.Feedback
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.usecases.GetFeedbacksUseCase
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.usecases.GetTokenUseCase
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.app.App
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.base.BaseViewModel
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.GuestBookRouter
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class FeedBackListViewModel : BaseViewModel<GuestBookRouter>() {
    var adapter: FeedBackListAdapter? = FeedBackListAdapter()
    val isProgressEnabled = ObservableBoolean(false)
    private var token: String = ""
    private var userId: String = ""
    private var page = 0
    @Inject
    lateinit var getFeedbacksUseCase: GetFeedbacksUseCase
    @Inject
    lateinit var getTokenUseCase: GetTokenUseCase

    init {
        loadItems()
        loadItems()
        adapter?.onItemClickListener = object : FeedBackListAdapter.OnItemClickListener {
            override fun onItemClick(feedback: Feedback) {
                router!!.goToAnswerList(feedback.comment_id!!)
            }
        }
    }

    fun onClickAdd() {
        router!!.goToAddFeedback()
    }

    fun setToken() {
        App.appComponent.inject(this)
        val disposable = getTokenUseCase.getToken().subscribeBy(
                onSuccess = {
                    token = it.apiToken
                    userId = it.userId
                    router!!.startService(token, userId)
                },
                onError = {
                }
        )
        addToDisposable(disposable)
    }

    fun loadItems() {
        App.appComponent.inject(this)
        isProgressEnabled.set(true)
        val disposable = getFeedbacksUseCase.get(page).subscribeBy(
                onNext = {
                    adapter?.listData!!.addAll(it.toMutableList())
                    isProgressEnabled.set(false)
                },
                onError = {
                    isProgressEnabled.set(false)
                    router?.showError(it)
                }
        )
        addToDisposable(disposable)
        page++
    }
}