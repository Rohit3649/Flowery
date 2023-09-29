package com.example.flowery.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flowery.ui.theme.screens.CartScreen
import com.example.flowery.ui.theme.screens.HomeScreen
import com.example.flowery.ui.theme.screens.HomeScreen2
import com.example.flowery.ui.theme.screens.ProductDetailScreen
import com.example.flowery.ui.theme.screens.ProductDetailScreen2
import com.example.flowery.ui.theme.screens.StartScreen

@Composable
fun Navigation() {
    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = Start) {
        composable(route = Start) {
            StartScreen(navHostController)
        }
        composable(route = Home) {
            HomeScreen(navHostController)
        }
        composable(route = ProductDetail) {
            ProductDetailScreen(navHostController)
        }
        composable(route = Cart) {
            CartScreen(navHostController)
        }
        composable(route = Home2) {
            HomeScreen2(navHostController)
        }
        composable(route = ProductDetail2) {
            ProductDetailScreen2(navHostController)
        }
    }
}

const val Start = "StartScreen"
const val Home = "HomeScreen"
const val ProductDetail = "ProductDetailScreen"
const val Cart = "CartScreen"
const val Home2 = "HomeScreen2"
const val ProductDetail2 = "ProductDetailScreen2"