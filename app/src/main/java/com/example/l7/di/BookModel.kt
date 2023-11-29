package com.example.l7.di

import com.example.l7.repository.BookRepository
import com.example.l7.service.BookApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BookModel {

    @Provides
    @Singleton
    fun provideRetrofitService(): BookApiService {
        return Retrofit.Builder()
            .baseUrl("http://192.168.0.104:8080/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideBookRepository(apiService: BookApiService): BookRepository {
        return BookRepository(apiService)
    }
}
