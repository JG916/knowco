package com.example.knowco.data.source.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinRankService {
    @GET("coin/{id}}")
    fun getCoin(@Path(value = "id") coinId: Int): Call<Any>
}