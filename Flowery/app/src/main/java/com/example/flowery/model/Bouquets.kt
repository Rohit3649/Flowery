package com.example.flowery.model

import androidx.annotation.DrawableRes
import com.example.flowery.R

data class Bouquets(
    val id: Int = 1,
    @DrawableRes val imageLink: Int = R.drawable.bouquet1,
    val tags: List<String> = listOf<String>("rose", "love", "see")
)

val listOfBouquet = listOf(
    Bouquets(1, R.drawable.bouquet1, listOf<String>("rose", "love", "see")),
    Bouquets(2, R.drawable.bouquet2, listOf<String>("flower", "Orchid")),
    Bouquets(3, R.drawable.bouquet3, listOf<String>("flower", "night")),
    Bouquets(4, R.drawable.bouquet4, listOf<String>("flower", "lilli")),
    Bouquets(5, R.drawable.bouquet5, listOf<String>("flower", "beautiful")),
    Bouquets(6, R.drawable.bouquet6, listOf<String>("flower", "Tulip")),
    Bouquets(7, R.drawable.bouquet7, listOf<String>("flower", "beautiful")),
    Bouquets(8, R.drawable.bouquet8, listOf<String>("flower", "beautiful")),
    Bouquets(9, R.drawable.bouquet9, listOf<String>("flower", "beautiful")),
)
