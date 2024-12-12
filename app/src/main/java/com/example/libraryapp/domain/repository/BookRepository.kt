package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.Book

interface BookRepository {

    suspend fun getBooks(): List<Book>

    suspend fun getBookById(bookId: Int): Book

    suspend fun addBook(book: Book)

}