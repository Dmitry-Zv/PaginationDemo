package com.example.pagination3demo.domain.usecase

import androidx.paging.PagingData
import com.example.pagination3demo.domain.model.UnsplashImage
import com.example.pagination3demo.domain.repository.UnsplashRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchImages @Inject constructor(private val repository: UnsplashRepository) {

    operator fun invoke(query: String): Flow<PagingData<UnsplashImage>> =
        repository.searchImages(query = query)
}