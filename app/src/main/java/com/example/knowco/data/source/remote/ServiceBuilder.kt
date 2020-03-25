package com.example.knowco.data.source.remote

import com.example.knowco.data.model.Coin
import com.google.gson.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


object ServiceBuilder {
    private const val COIN_RANK_URL = "https://api.coinranking.com/v1/public/"

    fun <T> createService(serviceClass: Class<T>): T {
        val gson = GsonBuilder()
            .registerTypeAdapter(Coin::class.java, CoinRankDeserializer<Coin>())
            .create()

        return Retrofit.Builder()
            .baseUrl(COIN_RANK_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(serviceClass)
    }

    private class CoinRankDeserializer<T> : JsonDeserializer<T> {
        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): T {
            val content: JsonObject? = json?.asJsonObject
                ?.get("data")?.asJsonObject
                ?.get(("coin"))?.asJsonObject
            return Gson().fromJson(content, typeOfT)
        }
    }
}