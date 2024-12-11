package com.example.libraryapp.data.repository

import com.example.libraryapp.data.datasource.LocalBookDataSource
import com.example.libraryapp.domain.model.Book
import com.example.libraryapp.domain.repository.BookRepository

class BookRepositoryImpl: BookRepository {

    private val localDataSource = LocalBookDataSource()

    override suspend fun getBooks(): List<Book> {
        return try {
            localDataSource.getBooks()
        } catch (e: Exception) {
            throw Exception("Error buscando libros", e)
        }
    }

    override suspend fun getBookById(bookId: Int): Book {
        val book = getBooks().find { book -> book.id == bookId}
        return book ?: throw Exception("Error: Libro no encontrado")
    }

}