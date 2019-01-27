package com.gmail.dudarenka.vitali.guestbooktestapp.data.net

import com.gmail.dudarenka.vitali.guestbooktestapp.data.entity.*
import com.google.gson.GsonBuilder
import io.reactivex.Completable
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*


class RestService(private val apiUrl: String) {
    private val restApi: RestApi

    init {
        val trustManager = createInsecureTrustManager()
        val sslSocketFactory = createInsecureSslSocketFactory(trustManager)
        val okHttpBuilder = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .sslSocketFactory(sslSocketFactory, trustManager)
                .hostnameVerifier(createInsecureHostnameVerifier())
        okHttpBuilder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        val gson = GsonBuilder()
                .create()
        val retrofit = Retrofit.Builder()
                .baseUrl(apiUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpBuilder.build())
                .build()
        restApi = retrofit.create(RestApi::class.java)
    }

    fun login(login: LoginRequest): Observable<Token> {
        return restApi.login("application/json", login)
    }

    fun registration(registration: RegistrationRequest): Observable<Token> {
        return restApi.registration("application/json", registration)
    }

    private fun createInsecureTrustManager(): X509TrustManager {
        return object : X509TrustManager {

            override fun getAcceptedIssuers(): Array<X509Certificate?> {
                return arrayOfNulls(0)
            }

            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}

            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}
        }
    }

    private fun createInsecureSslSocketFactory(trustManager: TrustManager): SSLSocketFactory {
        try {
            val context = SSLContext.getInstance("TLS")
            context.init(null, arrayOf<TrustManager>(trustManager), null)
            return context.socketFactory
        } catch (e: Exception) {
            throw AssertionError(e)
        }

    }

    private fun createInsecureHostnameVerifier(): HostnameVerifier {
        return HostnameVerifier { s, sslSession -> true }
    }

    fun getAll(token: String, page: Int): Observable<FeedbackPageResponse> {
        return restApi.getAll("application/json", token, page)
    }

    fun addFeefback(feedbackRequest: FeedbackRequest): Observable<CommentResponse> {
        return restApi.addFeedback("application/json", feedbackRequest)
    }

    fun getAnswers(token: String, comment: String): Observable<List<FeedbackResponse>> {
        return restApi.getAnswers("application/json", comment, token)
    }

    fun addAnswer(feedbackRequest: FeedbackRequest, comment: String): Observable<FeedbackResponse> {
        return restApi.addAnswer("application/json", feedbackRequest, comment)
    }
}