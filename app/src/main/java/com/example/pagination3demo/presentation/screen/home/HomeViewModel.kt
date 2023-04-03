package com.example.pagination3demo.presentation.screen.home

import androidx.lifecycle.ViewModel
import com.example.pagination3demo.domain.usecase.GetAllImages
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(getAllImages: GetAllImages) : ViewModel() {
    val images = getAllImages()
}