package com.example.pagination3demo.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.pagination3demo.data.local.UnsplashDatabase
import com.example.pagination3demo.data.remote.UnsplashApi
import com.example.pagination3demo.domain.model.UnsplashImage
import com.example.pagination3demo.domain.model.UnsplashRemoteKeys
import com.example.pagination3demo.util.Constants.ITEMS_PER_PAGE

@OptIn(ExperimentalPagingApi::class)
class UnsplashRemoteMediator(
    private val unsplashApi: UnsplashApi,
    private val unsplashDatabase: UnsplashDatabase
) : RemoteMediator<Int, UnsplashImage>() {

    private val unsplashRemoteKeysDao = unsplashDatabase.unsplashRemoteKeysDao()
    private val unsplashImageDao = unsplashDatabase.unsplashImageDao()
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UnsplashImage>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }

            }

            val response = unsplashApi.getAllImages(page = currentPage, perPage = ITEMS_PER_PAGE)
            val endOfPaginationReached = response.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            unsplashDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    unsplashImageDao.deleteAllImages()
                    unsplashRemoteKeysDao.deleteAllRemoteKeys()
                }

                val keys = response.map {
                    UnsplashRemoteKeys(
                        id = it.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                unsplashRemoteKeysDao.addAllRemoteKeys(keys)
                unsplashImageDao.addImages(response)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, UnsplashImage>): UnsplashRemoteKeys? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { unsplashImage ->
            unsplashRemoteKeysDao.getRemoteKeys(id = unsplashImage.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, UnsplashImage>): UnsplashRemoteKeys? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { unsplashImage ->
            unsplashRemoteKeysDao.getRemoteKeys(id = unsplashImage.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, UnsplashImage>): UnsplashRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                unsplashRemoteKeysDao.getRemoteKeys(id)
            }

        }
    }
}