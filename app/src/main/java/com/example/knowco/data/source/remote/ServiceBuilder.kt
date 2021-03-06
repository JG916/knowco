package com.example.knowco.data.source.remote

import com.example.knowco.data.model.Coin
import com.example.knowco.data.model.CoinList
import com.google.gson.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


object ServiceBuilder {
    private const val COIN_RANK_URL = "https://api.coinranking.com/v1/public/"

    fun <T> createService(serviceClass: Class<T>): T {
        val gson = GsonBuilder()
            .registerTypeAdapter(Coin::class.java, CoinDeserializer())
            .registerTypeAdapter(CoinList::class.java, CoinListDeserializer())
            .create()

        return Retrofit.Builder()
            .baseUrl(COIN_RANK_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(createInterceptor())
            .build()
            .create(serviceClass)
    }

    private fun createInterceptor(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    private class CoinDeserializer: JsonDeserializer<Coin> {
        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): Coin {
            val content: JsonObject? = json?.asJsonObject
                ?.get("data")?.asJsonObject
                ?.get(("coin"))?.asJsonObject
            return Gson().fromJson(content, typeOfT)
        }
    }

    private class CoinListDeserializer : JsonDeserializer<CoinList> {
        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): CoinList {
            val content: JsonObject? = json?.asJsonObject
                ?.get("data")?.asJsonObject
            return Gson().fromJson(content, typeOfT)
        }
    }
}