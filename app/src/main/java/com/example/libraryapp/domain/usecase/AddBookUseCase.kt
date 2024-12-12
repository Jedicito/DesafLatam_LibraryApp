package com.example.libraryapp.domain.usecase

import com.example.libraryapp.domain.model.Book
import com.example.libraryapp.domain.repository.BookRepository

class AddBookUseCase(private val repository: BookRepository) {
    suspend operator fun invoke(
        title: String = "",
        author: String = "",
        year: Int = 0,
        description: String = "",
        imageUrl: String? = null
    ) {
// Validaciones
        require(title.isNotBlank()) {  "Titulo no puede estar vacío" }
        require(author.isNotBlank()) { "Autor no puede estar vacío" }
        require(year > 0) { "Año inválido" }
        val book = Book(
            id = 0, // Repository asignará ID
            title = title,
            author = author,
            year = year,
            description = description,
            imageUrl = imageUrl,
            isAvailable = true
        )
        repository.addBook(book)
    }
}