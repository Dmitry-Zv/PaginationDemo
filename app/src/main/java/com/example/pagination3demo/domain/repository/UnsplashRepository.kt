package com.example.pagination3demo.domain.repository

import androidx.paging.PagingData
import com.example.pagination3demo.domain.model.UnsplashImage
import kotlinx.coroutines.flow.Flow

interface UnsplashRepository {

    fun getAllImages():Flow<PagingData<UnsplashImage>>
}