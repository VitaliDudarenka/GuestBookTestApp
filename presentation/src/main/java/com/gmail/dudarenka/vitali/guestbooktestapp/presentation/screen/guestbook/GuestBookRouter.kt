package com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook

import android.content.Intent
import android.widget.Toast
import com.gmail.dudarenka.vitali.guestbooktestapp.R
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.base.BaseRouter
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.addAnswer.AddAnswerFragment
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.addFeedback.AddFeedbackFragment
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.answers.AnswerListFragment
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.feedbacks.FeedBackListFragment
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.registration.RegistrationFragment
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.start.StartFragment
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.services.SocketService


class GuestBookRouter(activity: GuestBookActivity) : BaseRouter<GuestBookActivity>(activity) {

    fun goToSartScreen() {
        replaceFragment(activity.supportFragmentManager, StartFragment.getInstance(), R.id.container, false)
    }

    fun goToRegistration() {
        replaceFragment(activity.supportFragmentManager, RegistrationFragment.getInstance(), R.id.container, true)
    }

    fun goToFeedbackList() {
        replaceFragment(activity.supportFragmentManager, FeedBackListFragment.getInstance(), R.id.container, true)
    }

    fun goToAnswerList(commentId: String) {
        replaceFragment(activity.supportFragmentManager, AnswerListFragment.getInstance(commentId), R.id.container, true)
    }

    fun goToAddAnswer(commentId: String) {
        replaceFragment(activity.supportFragmentManager, AddAnswerFragment.getInstance(commentId), R.id.container, true)
    }

    fun goToAddFeedback() {
        replaceFragment(activity.supportFragmentManager, AddFeedbackFragment.getInstance(), R.id.container, true)
    }

    fun showSaveError() {
        Toast.makeText(activity, activity.getString(R.string.fill_forms), Toast.LENGTH_SHORT).show()
    }

    fun showPasswordError() {
        Toast.makeText(activity, activity.getString(R.string.edit_password), Toast.LENGTH_SHORT).show()
    }

    fun showRegComplete() {
        Toast.makeText(activity, "Registration complete", Toast.LENGTH_SHORT).show()
    }

    fun startService(token: String, userId: String) {
        activity.startService(Intent(activity, SocketService::class.java).putExtra("token", token).putExtra("id", userId))

    }

}