package com.popularmovies.networking

import com.google.gson.GsonBuilder
import com.popularmovies.utils.AppConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


object NetworkingHelper {
    private val clientBuilder = OkHttpClient.Builder()
    fun getInstance(): APIServices {
        clientBuilder.addInterceptor { chain ->
            val request = chain.request()
            val response = chain.proceed(request)
            response
        }
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .client(clientBuilder.build())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(
                GsonBuilder()
                    .setLenient()
                    .create()))
            .build().create(APIServices::class.java)
    }


}