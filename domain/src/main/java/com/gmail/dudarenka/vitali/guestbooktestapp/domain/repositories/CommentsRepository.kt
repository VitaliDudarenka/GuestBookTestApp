package com.gmail.dudarenka.vitali.guestbooktestapp.domain.repositories

import com.gmail.dudarenka.vitali.guestbooktestapp.domain.entity.Feedback
import io.reactivex.Completable
import io.reactivex.Observable

interface CommentsRepository : BaseRepository {

    fun getAll(page: Int): Observable<List<Feedback>>
    fun getAnswers(comment: String): Observable<List<Feedback>>
    fun addFeedback(feedback: Feedback): Observable<Feedback>
    fun addAnswer(feedback: Feedback, comment: String): Observable<Feedback>
}