package com.gmail.dudarenka.vitali.guestbooktestapp.domain.usecases

import com.gmail.dudarenka.vitali.guestbooktestapp.domain.entity.Feedback
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.executor.PostExecutorThread
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.repositories.CommentsRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetFeedbacksUseCase @Inject constructor(postExecutorThread: PostExecutorThread,
                                              private val commentsRepository: CommentsRepository) : BaseUseCase(postExecutorThread) {

    fun get(page: Int): Observable<List<Feedback>> {
        return commentsRepository.getAll(page).observeOn(postExecutorThread).subscribeOn(workExecutorThread)
    }

}



