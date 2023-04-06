package com.example.pagination3demo.presentation.screen.search

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pagination3demo.presentation.screen.common.ListContent

@Composable
fun SearchScreen(navController: NavController, viewModel: SearchViewModel = hiltViewModel()) {

    val searchState = viewModel.searchState.collectAsLazyPagingItems()
    val queryImage by viewModel.searchQuery
    Log.d("SEARCH_STATE", searchState.toString())

    Scaffold(
        topBar = {
            SearchTopBar(
                text = queryImage,
                onTextChange = {
                    viewModel.onEvent(event = SearchEvent.UpdateText(it))
                },
                onSearchClicked = {
                    viewModel.onEvent(event = SearchEvent.UpdateSearch(it))
                },
                onCloseClicked = {
                    navController.popBackStack()
                }
            )
        },
        content = {
            ListContent(
                items = searchState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            )
        }
    )

}