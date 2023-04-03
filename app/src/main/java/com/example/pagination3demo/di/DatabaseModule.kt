package com.example.pagination3demo.di

import android.content.Context
import androidx.room.Room
import com.example.pagination3demo.data.local.UnsplashDatabase
import com.example.pagination3demo.data.local.dao.UnsplashImageDao
import com.example.pagination3demo.data.local.dao.UnsplashRemoteKeysDao
import com.example.pagination3demo.util.Constants.UNSPLASH_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideUnsplashDatabase(@ApplicationContext context: Context): UnsplashDatabase =
        Room.databaseBuilder(context, UnsplashDatabase::class.java, UNSPLASH_DATABASE)
            .build()



}