package com.example.chattingappui.ui.theme.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp


@Composable
fun IconComponentDrawable(
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
    size: Dp,
    tint: Color = Color.Unspecified,
    onClick: () -> Unit = {}
) {
    Icon(
        painter = painterResource(id = icon),
        contentDescription = "",
        modifier = modifier
            .size(size)
            .clickable { onClick() },
        tint = tint
    )
}

@Composable
fun IconComponentDrawableNoRippleEffect(
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
    size: Dp,
    tint: Color = Color.Unspecified,
    onClick: () -> Unit = {}
) {
    Icon(
        painter = painterResource(id = icon),
        contentDescription = "",
        modifier = modifier
            .size(size)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() },
        tint = tint
    )
}

@Composable
fun IconComponentImageVector(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    size: Dp,
    tint: Color = Color.Unspecified,
    onClick: () -> Unit = {}
) {
    Icon(
        imageVector = icon,
        contentDescription = "",
        modifier = modifier
            .size(size)
            .clickable { onClick() },
        tint = tint
    )
}