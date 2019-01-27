package com.gmail.dudarenka.vitali.guestbooktestapp.presentation.executor

import com.gmail.dudarenka.vitali.guestbooktestapp.domain.executor.PostExecutorThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class UIThread : PostExecutorThread {
    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}