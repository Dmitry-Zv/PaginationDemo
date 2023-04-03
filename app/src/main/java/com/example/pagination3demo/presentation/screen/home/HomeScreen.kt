package com.example.pagination3demo.presentation.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pagination3demo.presentation.navigation.Screen
import com.example.pagination3demo.presentation.screen.common.ListContent

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    val images = viewModel.images.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar {
                navController.navigate(Screen.Search.route)
            }
        },
        content = {
            ListContent(items = images, modifier = Modifier
                .fillMaxSize()
                .padding(it))
        }
    )

}