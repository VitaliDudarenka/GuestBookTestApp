package com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.start

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.gmail.dudarenka.vitali.guestbooktestapp.R
import com.gmail.dudarenka.vitali.guestbooktestapp.databinding.FragmentStartBinding
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.base.BaseMvvmFragment
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.GuestBookRouter


class StartFragment : BaseMvvmFragment<StartViewModel, GuestBookRouter, FragmentStartBinding>() {

    companion object {
        fun getInstance(): StartFragment {
            return StartFragment()
        }
    }

    override fun provideViewModel(): StartViewModel {
        return ViewModelProviders.of(this).get(StartViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_start

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}