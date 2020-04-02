package com.example.knowco.data.model

class CoinList(
    val coins: List<Coin>,
    val stats: Stats
) {
    data class Stats(
        val offset: Int
    )
}
