package com.example.knowco.data.source.remote

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val COIN_RANK_URL = "https://api.coinranking.com/v1/public/"

    fun <T> createService(serviceClass: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(COIN_RANK_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(serviceClass)
    }

}