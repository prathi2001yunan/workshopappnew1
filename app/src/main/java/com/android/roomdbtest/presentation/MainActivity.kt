package com.android.roomdbtest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.android.roomdbtest.presentation.navigation.NavGraph

import com.android.roomdbtest.presentation.viewmodels.LoginViewModel
import com.android.roomdbtest.presentation.viewmodels.ProfileViewModel
import com.android.roomdbtest.presentation.viewmodels.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val noteViewModel = viewModels<RegisterViewModel> ()
    private val loginViewModel = viewModels<LoginViewModel> ()
    private val profileViewModel = viewModels<ProfileViewModel> ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           MaterialTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = col1)

                ) {
                    NavGraph(
                        noteViewModel = noteViewModel.value,
                        loginViewModel = loginViewModel.value,
                        profileViewModel = profileViewModel.value
                    )
                }
            }
        }
    }
}
val col1 = Color(0xFFB2A4FF)
