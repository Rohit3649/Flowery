package com.example.flowery.model

import androidx.annotation.DrawableRes
import com.example.flowery.R

data class CheckoutItem(
    val id: Int,
    @DrawableRes val image: Int,
    val name: String,
    val quantity: Int,
    val price: String
)

val CheckoutItemList = listOf(
    CheckoutItem(1, R.drawable.bouquet1, "bouquet1", 2, "1120 Rs."),
    CheckoutItem(2, R.drawable.bouquet2, "bouquet2", 2, "1210 Rs."),
    CheckoutItem(3, R.drawable.bouquet3, "bouquet3", 2, "1600 Rs."),
    CheckoutItem(4, R.drawable.bouquet4, "bouquet4", 2, "1220 Rs."),
    CheckoutItem(5, R.drawable.bouquet5, "bouquet5", 2, "1240 Rs."),
)