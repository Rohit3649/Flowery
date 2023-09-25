package com.example.flowery.model

import androidx.compose.ui.graphics.Color
import com.example.flowery.ui.theme.Pink

data class Category(
    val id:Int,
    val text:String,
    val color:Color
)


val colorCategory = listOf(
    Category(1, "Red", Color.Red),
    Category(2, "Yellow", Color.Yellow),
    Category(3, "Gray", Color.Gray),
    Category(4, "Green", Color.Green),
    Category(5, "Blue", Color.Blue),
    Category(6, "Pink", Pink),
)