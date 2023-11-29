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
class AddBookActivity : AppCompatActivity() {

    private lateinit var editTextBookTitle: EditText
    private lateinit var editTextBookAuthor: EditText
    private lateinit var editTextBookYear: EditText
    private lateinit var buttonAddBook: Button
    private val bookViewModel: BookViewModel by viewModels()
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book) // Replace with your layout name


        // Bind views
        editTextBookTitle = findViewById(R.id.editTextBookTitle)
        editTextBookAuthor = findViewById(R.id.editTextBookAuthor)
        editTextBookYear = findViewById(R.id.editTextBookYear)
        buttonAddBook = findViewById(R.id.buttonAddBook)
        backButton = findViewById(R.id.backButton)

        // Set onClickListener for button
        buttonAddBook.setOnClickListener { addBook() }
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addBook() {
        val title = editTextBookTitle.text.toString()
        val author = editTextBookAuthor.text.toString()
        val yearStr = editTextBookYear.text.toString()

        // Validation or conversion to int can be added here for year
        val year = yearStr.toIntOrNull() ?: 0 // Default to 0 or handle error

        // Create book object - Assuming a Book class is already defined
        val book = Book(title = title, author = author, year = year)

        // Update ViewModel
        bookViewModel.addBook(book)
    }
}
