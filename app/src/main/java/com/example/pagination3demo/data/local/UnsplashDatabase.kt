package com.example.pagination3demo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pagination3demo.data.local.dao.UnsplashImageDao
import com.example.pagination3demo.data.local.dao.UnsplashRemoteKeysDao
import com.example.pagination3demo.domain.model.UnsplashImage
import com.example.pagination3demo.domain.model.UnsplashRemoteKeys


@Database(entities = [UnsplashImage::class, UnsplashRemoteKeys::class], version = 1)
abstract class UnsplashDatabase : RoomDatabase() {

    abstract fun unsplashImageDao(): UnsplashImageDao
    abstract fun unsplashRemoteKeysDao(): UnsplashRemoteKeysDao
}