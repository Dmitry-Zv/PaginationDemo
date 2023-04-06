package com.example.pagination3demo.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pagination3demo.data.local.UnsplashDatabase
import com.example.pagination3demo.data.paging.SearchPagingSource
import com.example.pagination3demo.data.paging.UnsplashRemoteMediator
import com.example.pagination3demo.data.remote.UnsplashApi
import com.example.pagination3demo.domain.model.UnsplashImage
import com.example.pagination3demo.domain.repository.UnsplashRepository
import com.example.pagination3demo.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UnsplashRepositoryImpl @Inject constructor(
    private val unsplashApi: UnsplashApi,
    private val unsplashDatabase: UnsplashDatabase
) : UnsplashRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getAllImages(): Flow<PagingData<UnsplashImage>> {
        val pagingSourceFactory = { unsplashDatabase.unsplashImageDao().getAllImages() }

        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = UnsplashRemoteMediator(
                unsplashApi, unsplashDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchImages(query: String): Flow<PagingData<UnsplashImage>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE)
        ) {
            SearchPagingSource(unsplashApi, query = query)
        }.flow
    }
}