package com.gmail.dudarenka.vitali.guestbooktestapp.domain.usecases

import com.gmail.dudarenka.vitali.guestbooktestapp.domain.entity.Login
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.entity.Registration
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.executor.PostExecutorThread
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.repositories.AuthRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class LoginUseCase @Inject constructor(postExecutorThread: PostExecutorThread,
                                       private val authRepository: AuthRepository) : BaseUseCase(postExecutorThread) {

    fun login(login: Login): Completable {
        return authRepository.login(login).observeOn(postExecutorThread).subscribeOn(workExecutorThread)
    }

}




