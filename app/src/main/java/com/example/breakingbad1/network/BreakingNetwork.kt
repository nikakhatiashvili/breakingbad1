package com.example.breakingbad1.network

import com.example.breakingbad1.model.Characters
import retrofit2.Response
import retrofit2.http.GET

interface BreakingNetwork {

    @GET("characters")
    suspend fun getAllCharacters(): Response<List<Characters>>

}