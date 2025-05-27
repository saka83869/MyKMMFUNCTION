package com.abcvipapp.mykmmfunction.android.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.abcvipapp.mykmmfunction.android.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreenView(navController: NavHostController) {

    LaunchedEffect(Unit) {
        delay(1500)
        navController.navigate("main_ui") {
            popUpTo("splash_ui") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        contentAlignment = Alignment.Center
    ) {

    }

}