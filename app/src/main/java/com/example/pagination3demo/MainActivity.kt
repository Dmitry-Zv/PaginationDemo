package com.example.pagination3demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pagination3demo.presentation.navigation.NavigationApp
import com.example.pagination3demo.ui.theme.Pagination3DemoTheme
import com.example.pagination3demo.ui.theme.topAppBarBackgroundColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            Pagination3DemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val systemUiController = rememberSystemUiController()
                    val appBarColor = MaterialTheme.colors.topAppBarBackgroundColor
                    SideEffect {
                        systemUiController.setSystemBarsColor(
                            color = appBarColor
                        )
                    }
                    NavigationApp()
                }
            }
        }
    }
}

