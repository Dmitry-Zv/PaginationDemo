package com.example.pagination3demo.domain.usecase

import androidx.paging.PagingData
import com.example.pagination3demo.domain.model.UnsplashImage
import com.example.pagination3demo.domain.repository.UnsplashRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllImages @Inject constructor(
    private val unsplashRepository: UnsplashRepository
) {
    operator fun invoke(): Flow<PagingData<UnsplashImage>> =
        unsplashRepository.getAllImages()

}