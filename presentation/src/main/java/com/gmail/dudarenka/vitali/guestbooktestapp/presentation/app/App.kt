package com.gmail.dudarenka.vitali.guestbooktestapp.presentation.app

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.inject.AppComponent
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.inject.AppModule
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.inject.DaggerAppComponent


class App : Application() {
    companion object {
        lateinit var instance: App
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    init {
        instance = this
    }

    override fun onCreate() {

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        super.onCreate()

    }
}