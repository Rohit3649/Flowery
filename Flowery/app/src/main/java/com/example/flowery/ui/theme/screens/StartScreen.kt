package com.example.flowery.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.chattingappui.ui.theme.component.IconComponentDrawable
import com.example.chattingappui.ui.theme.component.SpacerHeight
import com.example.flowery.R
import com.example.flowery.ui.theme.backGround
import com.example.flowery.ui.theme.component.TextComponentId
import com.example.flowery.ui.theme.navigation.Home
import com.example.flowery.ui.theme.navigation.Home2
import com.example.flowery.ui.theme.textPinkColor
import kotlinx.coroutines.delay

@Composable
fun StartScreen(navHostController: NavHostController) {

    LaunchedEffect(key1 = Unit) {
        delay(2000)
        navHostController.navigate(Home2)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backGround),
        contentAlignment = Alignment.Center
    ) {
        Column {
            IconComponentDrawable(icon = R.drawable.flowerbg2, size = 400.dp)
            SpacerHeight(30.dp)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                contentAlignment = Alignment.Center,
            ) {
                TextComponentId(
                    text = R.string.flower_quote,
                    fontSize = 25.sp,
                    color = textPinkColor
                )
            }

        }
    }
}

@Preview(
    showBackground = true,
    name = "preview"/*,
    widthDp = 200,
    heightDp = 200*/
)
@Composable
fun ShowPreviewStartScreen() {
    StartScreen(rememberNavController())
}