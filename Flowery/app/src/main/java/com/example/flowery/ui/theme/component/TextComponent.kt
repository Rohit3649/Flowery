package com.example.flowery.ui.theme.component

import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit


@Composable
fun TextComponentString(
    text: String,
    fontSize: TextUnit,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.W600,
    color: Color = Color.Black
) {
    Text(
        text = text,
        modifier = modifier,
        style = TextStyle(
            fontSize = fontSize,
            fontWeight = fontWeight,
            color = color
        ),
    )
}

@Composable
fun TextComponentId(
    @StringRes text: Int,
    modifier: Modifier = Modifier,
    fontSize: TextUnit,
    fontWeight: FontWeight = FontWeight.W600,
    color: Color = Color.Black,
    textAlign: TextAlign = TextAlign.Justify
) {
    Text(
        text = stringResource(id = text),
        style = TextStyle(
            fontSize = fontSize,
            fontWeight = fontWeight,
            color = color
        ),
        textAlign = textAlign,
        modifier = modifier
    )
}