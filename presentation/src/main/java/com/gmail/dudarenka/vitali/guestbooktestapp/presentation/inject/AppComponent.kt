package com.gmail.dudarenka.vitali.guestbooktestapp.presentation.inject

import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.GuestBookViewModel
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.addAnswer.AddAnswerViewModel
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.addFeedback.AddFeedbackViewModel
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.answers.AnswerListViewModel
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.feedbacks.FeedBackListViewModel
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.registration.RegistrationViewModel
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.start.StartViewModel
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(registrationViewModel: RegistrationViewModel)
    fun inject(startViewModel: StartViewModel)
    fun inject(feedBackListViewModel: FeedBackListViewModel)
    fun inject(addFeedbackViewModel: AddFeedbackViewModel)
    fun inject(guestBookViewModel: GuestBookViewModel)
    fun inject(answerListViewModel: AnswerListViewModel)
    fun inject(addAnswerViewModel: AddAnswerViewModel)
}