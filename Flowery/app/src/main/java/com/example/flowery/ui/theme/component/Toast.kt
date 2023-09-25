package com.example.flowery.ui.theme.component

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


@Composable
fun ShowToast() {
    val context = LocalContext.current

    val toast = Toast.makeText(context, "This is a toast message!", Toast.LENGTH_SHORT)
    toast.show()
}