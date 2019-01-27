package com.gmail.dudarenka.vitali.guestbooktestapp.domain.usecases

import com.gmail.dudarenka.vitali.guestbooktestapp.domain.executor.PostExecutorThread
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.repositories.AuthRepository
import io.reactivex.Single
import javax.inject.Inject

class IsAdminUseCase @Inject constructor(postExecutorThread: PostExecutorThread,
                                         private val authRepository: AuthRepository) : BaseUseCase(postExecutorThread) {

    fun isAdmin(): Single<Int> = Single.just(authRepository.isAdmin())

}




