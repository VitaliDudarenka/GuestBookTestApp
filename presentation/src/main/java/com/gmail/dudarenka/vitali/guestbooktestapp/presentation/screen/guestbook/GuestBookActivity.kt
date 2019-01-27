package com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.gmail.dudarenka.vitali.guestbooktestapp.R
import com.gmail.dudarenka.vitali.guestbooktestapp.databinding.ActivityGuestbookBinding
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.base.BaseMvvmActivity


class GuestBookActivity : BaseMvvmActivity<GuestBookViewModel, GuestBookRouter, ActivityGuestbookBinding>() {


    override fun provideRouter(): GuestBookRouter {
        return GuestBookRouter(this)
    }

    override fun provideViewModel(): GuestBookViewModel {
        return ViewModelProviders.of(this).get(GuestBookViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.activity_guestbook

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (viewModel.isLogin()) {
            router.goToFeedbackList()
        } else
            router.goToSartScreen()
    }

}


