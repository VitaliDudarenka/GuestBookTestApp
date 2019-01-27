package com.gmail.dudarenka.vitali.guestbooktestapp.domain.usecases

import com.gmail.dudarenka.vitali.guestbooktestapp.domain.entity.Feedback
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.executor.PostExecutorThread
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.repositories.CommentsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class AddAnswerUseCase @Inject constructor(postExecutorThread: PostExecutorThread,
                                           private val commentsRepository: CommentsRepository) : BaseUseCase(postExecutorThread) {

    fun addFeedback(feedback: Feedback, comment: String): Observable<Feedback> {
        return commentsRepository.addAnswer(feedback, comment).observeOn(postExecutorThread).subscribeOn(workExecutorThread)
    }

}



