package com.example.l7.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.l7.model.Book
import com.example.l7.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(private val repository: BookRepository) : ViewModel() {

    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> = _books

    private val _operationStatus = MutableLiveData<String>()
    val operationStatus: LiveData<String> = _operationStatus

    init {
        loadAllBooks()
    }

    fun loadAllBooks() {
        viewModelScope.launch {
            try {
                _books.value = repository.getAllBooks().content
                //
            } catch (e: Exception) {
                _operationStatus.value = "Failed to load books: ${e.message}"
            }
        }
    }

    fun addBook(book: Book) {
        viewModelScope.launch {
            try {
                val addBookOperation = async { repository.addBook(book) }
                addBookOperation.await()

                _operationStatus.value = "Book added successfully: ${book.title}"
                loadAllBooks() // Reload the book list to update UI
            } catch (e: Exception) {
                _operationStatus.value = "Failed to add book: ${e.message}"
            }
        }
    }

    fun updateBook(id: Long, book: Book) {
        viewModelScope.launch {
            try {
                val updateOperation = async { repository.updateBook(id, book) }
                updateOperation.await()

                _operationStatus.value = "Book updated successfully: ${book.title}"
                loadAllBooks() // Reload the book list to update UI
            } catch (e: Exception) {
                _operationStatus.value = "Failed to update book: ${e.message}"
            }
        }
    }

    fun deleteBook(id: Long) {
        viewModelScope.launch {
            try {
                val del = async { repository.deleteBook(id) }
                del.await()
                _operationStatus.value = "Book deleted successfully"
                loadAllBooks() // Reload the book list to update UI
            } catch (e: Exception) {
                _operationStatus.value = "Failed to delete book: ${e.message}"
            }
        }
    }
}
