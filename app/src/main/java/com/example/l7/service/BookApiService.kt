package com.example.l7.service

import com.example.l7.model.Book
import com.example.l7.model.BookApiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface BookApiService {

    @GET("books?size=100")
    suspend fun getAllBooks(): BookApiResponse

    @GET("books/{id}")
    suspend fun getBookById(@Path("id") id: Long): Book

    @POST("books")
    suspend fun addBook(@Body book: Book): Book

    @PUT("books/{id}")
    suspend fun updateBook(@Path("id") id: Long, @Body book: Book): Book

    @DELETE("books/{id}")
    suspend fun deleteBook(@Path("id") id: Long): Unit


}