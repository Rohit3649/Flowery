package com.example.flowery.ui.theme.component

import android.graphics.ColorFilter
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource

@Composable
fun ImageComponent(
    @DrawableRes image:Int,
    modifier: Modifier = Modifier,
    contentDescription: String = "",
    contentScale: ContentScale = ContentScale.Fit,
    alignment: Alignment = Alignment.Center,
) {
    Image(
        painter = painterResource(id = image),
        contentScale = contentScale,
        contentDescription = contentDescription,
        alignment = alignment,
        modifier = modifier
    )
}