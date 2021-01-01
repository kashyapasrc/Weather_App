package com.kashyap.weather.domain.networking.api

import com.kashyap.weather.domain.constants.UrlConstant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ClientCreator {


    private fun getRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = (HttpLoggingInterceptor.Level.BODY)
        val okhttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(logging)

        okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS)

        return Retrofit.Builder()
            .baseUrl(UrlConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val client: Client = getRetrofit().create(Client::class.java)

}