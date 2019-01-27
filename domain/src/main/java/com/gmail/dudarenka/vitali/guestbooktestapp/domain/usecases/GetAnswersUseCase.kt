package com.gmail.dudarenka.vitali.guestbooktestapp.domain.usecases

import com.gmail.dudarenka.vitali.guestbooktestapp.domain.entity.Feedback
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.executor.PostExecutorThread
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.repositories.CommentsRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetAnswersUseCase @Inject constructor(postExecutorThread: PostExecutorThread,
                                            private val commentsRepository: CommentsRepository) : BaseUseCase(postExecutorThread) {

    fun get(comment: String): Observable<List<Feedback>> {
        return commentsRepository.getAnswers(comment).observeOn(postExecutorThread).subscribeOn(workExecutorThread)
    }

}



