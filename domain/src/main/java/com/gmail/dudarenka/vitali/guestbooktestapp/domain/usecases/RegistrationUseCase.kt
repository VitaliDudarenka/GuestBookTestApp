package com.gmail.dudarenka.vitali.guestbooktestapp.domain.usecases

import com.gmail.dudarenka.vitali.guestbooktestapp.domain.entity.Registration
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.executor.PostExecutorThread
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.repositories.AuthRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(postExecutorThread: PostExecutorThread,
                                                   private val authRepository: AuthRepository) : BaseUseCase(postExecutorThread) {

    fun registration(registration: Registration): Completable {
        return authRepository.registration(registration).observeOn(postExecutorThread).subscribeOn(workExecutorThread)
    }

}




