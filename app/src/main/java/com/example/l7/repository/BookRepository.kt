package com.example.l7.repository

import com.example.l7.model.Book
import com.example.l7.service.BookApiService

class BookRepository(private val apiService: BookApiService) {

    suspend fun getAllBooks() = apiService.getAllBooks()

    suspend fun getBookById(id: Long) = apiService.getBookById(id)

    suspend fun addBook(book: Book) = apiService.addBook(book)

    suspend fun updateBook(id: Long, book: Book) = apiService.updateBook(id, book)

    suspend fun deleteBook(id: Long) = apiService.deleteBook(id)


}
