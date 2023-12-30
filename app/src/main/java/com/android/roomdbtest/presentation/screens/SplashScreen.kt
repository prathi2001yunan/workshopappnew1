package com.android.roomdbtest.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navcontroller: () -> Unit){
    LaunchedEffect(key1 = true){
        delay(5000L)
        navcontroller()
    }
    Splash()
}

