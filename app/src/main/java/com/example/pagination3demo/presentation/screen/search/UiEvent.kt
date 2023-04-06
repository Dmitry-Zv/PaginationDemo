package com.example.pagination3demo.presentation.screen.search


sealed class SearchEvent {
    data class UpdateText(val text: String) : SearchEvent()
    data class UpdateSearch(val query: String) : SearchEvent()

}

interface UiEvent<E> {

    fun onEvent(event: E)
}