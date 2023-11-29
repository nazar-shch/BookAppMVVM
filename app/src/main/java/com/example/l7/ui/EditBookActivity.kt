package com.example.l7.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.l7.R
import com.example.l7.model.Book
import com.example.l7.vm.BookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditBookActivity : AppCompatActivity() {

    private lateinit var bookToEdit: Book
    private val bookViewModel: BookViewModel by viewModels()

    private lateinit var editTextBookTitle: EditText
    private lateinit var editTextBookAuthor: EditText
    private lateinit var editTextBookYear: EditText
    private lateinit var buttonUpdateBook: Button
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_book)

        // Bind views
        editTextBookTitle = findViewById(R.id.editTextEditBookTitle)
        editTextBookAuthor = findViewById(R.id.editTextEditBookAuthor)
        editTextBookYear = findViewById(R.id.editTextEditBookYear)
        buttonUpdateBook = findViewById(R.id.buttonUpdateBook)
        backButton = findViewById(R.id.backButton)

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        bookToEdit = intent.getParcelableExtra<Book>("BOOK_EXTRA")!!

        editTextBookTitle.setText(bookToEdit.title)
        editTextBookAuthor.setText(bookToEdit.author)
        editTextBookYear.setText(bookToEdit.year.toString())

        buttonUpdateBook.setOnClickListener {
            val updatedBook = Book(
                bookToEdit.id,
                editTextBookTitle.text.toString(),
                editTextBookAuthor.text.toString(),
                editTextBookYear.text.toString().toInt()
            )
            bookViewModel.updateBook(bookToEdit.id.toLong(), updatedBook)
        }
    }
}
