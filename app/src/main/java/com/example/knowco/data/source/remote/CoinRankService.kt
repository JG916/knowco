package com.example.knowco.data.source.remote

import com.example.knowco.data.model.Coin
import com.example.knowco.data.model.CoinList
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinRankService {
    @GET("coin")
    suspend fun getCoins(): CoinList

    @GET("coin/{id}")
    suspend fun getCoin(@Path(value = "id") coinId: Int): Coin
}