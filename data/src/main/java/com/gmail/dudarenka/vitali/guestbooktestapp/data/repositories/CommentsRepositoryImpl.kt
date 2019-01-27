package com.gmail.dudarenka.vitali.guestbooktestapp.data.repositories

import com.gmail.dudarenka.vitali.guestbooktestapp.data.entity.CommentResponse
import com.gmail.dudarenka.vitali.guestbooktestapp.data.entity.transformToData
import com.gmail.dudarenka.vitali.guestbooktestapp.data.entity.transformToDomain
import com.gmail.dudarenka.vitali.guestbooktestapp.data.net.RestService
import com.gmail.dudarenka.vitali.guestbooktestapp.data.sharedPrefs.AppSharedPrefs
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.entity.Feedback
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.repositories.CommentsRepository
import io.reactivex.Observable


class CommentsRepositoryImpl(private val sharedPrefs: AppSharedPrefs, private val apiService: RestService) : CommentsRepository {


    override fun getAll(page: Int): Observable<List<Feedback>> {
        return apiService.getAll(sharedPrefs.getToken(), page).map {
            it.data.map {
                it.transformToDomain()
            }
        }
    }

    override fun addFeedback(feedback: Feedback): Observable<Feedback> {
        return apiService.addFeefback(feedback.transformToData(sharedPrefs.getToken())).map {
            it.transformToDomain()
        }
    }

    override fun getAnswers(comment: String): Observable<List<Feedback>> {
        return apiService.getAnswers(sharedPrefs.getToken(), comment).map {
            it.map {
                it.transformToDomain()
            }
        }
    }

    override fun addAnswer(feedback: Feedback, comment: String): Observable<Feedback> {
        return apiService.addAnswer(feedback.transformToData(sharedPrefs.getToken()), comment).map {
            it.transformToDomain()
        }
    }
}