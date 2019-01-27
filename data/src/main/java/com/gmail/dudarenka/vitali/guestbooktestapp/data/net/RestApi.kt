package com.gmail.dudarenka.vitali.guestbooktestapp.data.net

import com.gmail.dudarenka.vitali.guestbooktestapp.data.entity.*
import io.reactivex.Completable
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*
import retrofit2.http.POST


interface RestApi {
    @POST("api/v1/auth/login")
    fun login(@Header("Accept") content_type: String, @Body body: LoginRequest): Observable<Token>

    @POST("api/v1/auth/register")
    fun registration(@Header("Accept") content_type: String, @Body body: RegistrationRequest): Observable<Token>

    @GET("api/v1/comment")
    fun getAll(@Header("Accept") content_type: String, @Query("api_token") token: String,
               @Query("page") page: Int): Observable<FeedbackPageResponse>

    @POST("api/v1/comment")
    fun addFeedback(@Header("Accept") content_type: String, @Body body: FeedbackRequest): Observable<CommentResponse>

    @GET("api/v1/comment/{comment}/answer")
    fun getAnswers(@Header("Accept") content_type: String,
                   @Path("comment") comment: String,
                   @Query("api_token") token: String): Observable<List<FeedbackResponse>>

    @POST("api/v1/comment/{comment}/answer")
    fun addAnswer(@Header("Accept") content_type: String, @Body body: FeedbackRequest,
                  @Path("comment") comment: String): Observable<FeedbackResponse>
}