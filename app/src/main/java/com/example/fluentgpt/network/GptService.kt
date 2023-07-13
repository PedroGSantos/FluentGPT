package com.example.fluentgpt.network

import com.example.fluentgpt.data.ChatCompletionResponse
import com.example.fluentgpt.data.HistoricMessages
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.converter.gson.GsonConverterFactory

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


private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

//Endpoints
interface ApiService {
    @POST("chat/completions")
    suspend fun createChat(@Body post: HistoricMessages): Response<ChatCompletionResponse>;
}

//Create a singleton builder of retrofit
object GptApi {
    val retrofitService : ApiService by lazy { retrofit.create(ApiService::class.java) }
}

