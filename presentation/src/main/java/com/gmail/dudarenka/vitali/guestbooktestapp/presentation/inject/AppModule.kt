package com.gmail.dudarenka.vitali.guestbooktestapp.presentation.inject

import android.content.Context
import android.content.SharedPreferences
import com.gmail.dudarenka.vitali.guestbooktestapp.data.net.RestService
import com.gmail.dudarenka.vitali.guestbooktestapp.data.repositories.AuthRepositoryImpl
import com.gmail.dudarenka.vitali.guestbooktestapp.data.repositories.CommentsRepositoryImpl
import com.gmail.dudarenka.vitali.guestbooktestapp.data.sharedPrefs.AppSharedPrefs
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.executor.PostExecutorThread
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.repositories.AuthRepository
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.repositories.CommentsRepository
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.executor.UIThread
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class AppModule(val context: Context) {
    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideAuthRepository(appSharedPrefs: AppSharedPrefs,
                              restService: RestService): AuthRepository = AuthRepositoryImpl(appSharedPrefs, restService)

    @Provides
    fun provideCommentsRepository(appSharedPrefs: AppSharedPrefs,
                                  restService: RestService): CommentsRepository = CommentsRepositoryImpl(appSharedPrefs, restService)

    @Provides
    fun provideRestService(@Named(URL_INJECT_NAME_DEBUG) serverUrl: String): RestService = RestService(serverUrl)

    @Provides
    fun providePostExecutorThread(): PostExecutorThread = UIThread()

    @Provides
    fun provideSharedPrefs(context: Context): AppSharedPrefs = AppSharedPrefs(context)

    @Provides
    @Named(URL_INJECT_NAME_DEBUG)
    fun provideServerUrlDebug(): String = "http://pusher.cpl.by/"

    @Provides
    @Named(URL_INJECT_NAME_RELEASE)
    fun provideServerUrlRelease(): String = "http://pusher.cpl.by/"


}
