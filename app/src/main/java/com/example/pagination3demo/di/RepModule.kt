package com.example.pagination3demo.di

import com.example.pagination3demo.data.repository.UnsplashRepositoryImpl
import com.example.pagination3demo.domain.repository.UnsplashRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RepModule {

    @Binds
    @Singleton
    fun bindUnsplashRepository(unsplashRepositoryImpl: UnsplashRepositoryImpl): UnsplashRepository
}