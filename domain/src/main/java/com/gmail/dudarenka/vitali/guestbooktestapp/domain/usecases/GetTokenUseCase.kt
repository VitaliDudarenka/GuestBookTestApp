package com.gmail.dudarenka.vitali.guestbooktestapp.domain.usecases

import com.gmail.dudarenka.vitali.guestbooktestapp.domain.entity.Feedback
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.entity.TokenDomain
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.executor.PostExecutorThread
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.repositories.AuthRepository
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.repositories.CommentsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(postExecutorThread: PostExecutorThread,
                                          private val authRepository: AuthRepository) : BaseUseCase(postExecutorThread) {

    fun getToken(): Single<TokenDomain> = Single.just(authRepository.getToken())


}



