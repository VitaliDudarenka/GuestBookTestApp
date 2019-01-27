package com.gmail.dudarenka.vitali.guestbooktestapp.domain.executor

import io.reactivex.Scheduler

interface PostExecutorThread {
    fun getScheduler(): Scheduler
}