package com.example.knowco.data.source.remote

import com.example.knowco.data.model.Coin
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinRankService {
    @GET("coin/{id}")
    suspend fun getCoin(@Path(value = "id") coinId: Int): Coin
}