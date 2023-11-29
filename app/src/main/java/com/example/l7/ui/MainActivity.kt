package com.example.l7.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.l7.R
import com.example.l7.model.Book
import com.example.l7.vm.BookViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val bookViewModel: BookViewModel by viewModels()
    private lateinit var bookAdapter: BookAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddBook: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewBooks)
        fabAddBook = findViewById(R.id.fabAddBook)

        setupRecyclerView()
        observeViewModel()

        fabAddBook.setOnClickListener {
            val intent = Intent(this, AddBookActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        bookAdapter = BookAdapter { book, action ->
            when (action) {
                "edit" -> editBook(book)
                "delete" -> deleteBook(book)
            }
        }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = bookAdapter
        }
    }

    private fun observeViewModel() {
        bookViewModel.books.observe(this) { books ->
            bookAdapter.submitList(books)
        }
    }

    private fun editBook(book: Book) {
        val intent = Intent(this, EditBookActivity::class.java).apply {
            putExtra("BOOK_EXTRA", book)
        }
        startActivity(intent)
    }

    private fun deleteBook(book: Book) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Delete Book")
            .setMessage("Are you sure you want to delete this book?")
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Delete") { _, _ ->
                book.id?.let { bookViewModel.deleteBook(it.toLong()) }
            }
            .show()
    }
}
