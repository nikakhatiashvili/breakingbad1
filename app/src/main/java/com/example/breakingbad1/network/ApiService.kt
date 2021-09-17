package com.example.breakingbad1.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiService {

    val breakService by lazy { createBreakingBadService() }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private fun createBreakingBadService():BreakingNetwork{
        val retrofitBuilder = Retrofit.Builder()
        retrofitBuilder.baseUrl("https://www.breakingbadapi.com/api/")
        retrofitBuilder.client(
            OkHttpClient().newBuilder()
                .addInterceptor(loggingInterceptor)
                .build()
        )
        retrofitBuilder.addConverterFactory(mochiConvertor())
        return retrofitBuilder.build().create(BreakingNetwork::class.java)
    }


    private fun mochiConvertor()=
        MoshiConverterFactory.create(
            Moshi.Builder().addLast(KotlinJsonAdapterFactory())
                .build()
        )
}