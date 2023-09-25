package com.example.flowery.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.flowery.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Flower(
    val id: Int = 0,
    val detailInformation: String = "",
    @DrawableRes val imageLink: Int = R.drawable.flower,
    var isSelected: Boolean = false,
    val name: String = "Rose",
    val price: Double = 0.0,
) : Parcelable