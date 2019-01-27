package com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.addFeedback

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.gmail.dudarenka.vitali.guestbooktestapp.R
import com.gmail.dudarenka.vitali.guestbooktestapp.databinding.FragmentAddFeedbackBinding
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.base.BaseMvvmFragment
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.GuestBookRouter


class AddFeedbackFragment : BaseMvvmFragment<AddFeedbackViewModel, GuestBookRouter, FragmentAddFeedbackBinding>() {

    companion object {
        fun getInstance(): AddFeedbackFragment {
            return AddFeedbackFragment()
        }
    }

    override fun provideViewModel(): AddFeedbackViewModel {
        return ViewModelProviders.of(this).get(AddFeedbackViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_add_feedback

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}