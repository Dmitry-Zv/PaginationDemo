package com.example.pagination3demo.presentation.screen.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pagination3demo.domain.model.UnsplashImage
import com.example.pagination3demo.domain.usecase.SearchImages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchImages: SearchImages) : ViewModel(),
    UiEvent<SearchEvent> {

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchState = MutableStateFlow<PagingData<UnsplashImage>>(PagingData.empty())
    val searchState = _searchState

    override fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateText -> updateText(text = event.text)
            is SearchEvent.UpdateSearch -> updateSearch(query = event.query)
        }
    }

    private fun updateSearch(query: String) {
        viewModelScope.launch {
            searchImages(query).cachedIn(viewModelScope).collect{
                _searchState.value = it
            }
        }
    }

    private fun updateText(text: String) {
        _searchQuery.value = text
    }

}