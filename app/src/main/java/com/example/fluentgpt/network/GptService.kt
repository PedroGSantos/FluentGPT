package com.example.fluentgpt.network

import com.example.fluentgpt.data.ChatCompletionResponse
import com.example.fluentgpt.data.MessagesTest
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private const val BASE_URL =
    "https://api.openai.com/v1/"

val okHttpClient = OkHttpClient.Builder()
    .addInterceptor { chain ->
        val originalRequest = chain.request()
        val authenticatedRequest = originalRequest.newBuilder()
            .header("Authorization", " Bearer <CHAVE>")
            .build()
        chain.proceed(authenticatedRequest)
    }
    .build()


val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

interface ApiService {
    @POST("chat/completions")
    suspend fun createChat(@Body post: MessagesTest): Response<ChatCompletionResponse>;

    @GET("models")
    suspend fun listModels(): Response<com.example.fluentgpt.data.Response>;
}
