package com.example.libraryapp.domain.usecase

import com.example.libraryapp.data.repository.BookRepositoryProvider

object UseCaseProvider {
    private var getBooksUseCase: GetBooksUseCase? = null
    private var getBookByIdUseCase: GetBookByIdUseCase? = null
    private var addBookUseCase: AddBookUseCase? = null

    fun provideGetBooksUseCase(): GetBooksUseCase {
        if (getBooksUseCase == null) {
            getBooksUseCase = GetBooksUseCase()
        }
        return getBooksUseCase!!
    }

  fun provideGetBookByIdUseCase(): GetBookByIdUseCase {
        if (getBookByIdUseCase == null) {
            getBookByIdUseCase = GetBookByIdUseCase()
        }
        return getBookByIdUseCase!!
    }

    fun provideAddBookUseCase(): AddBookUseCase {
        if (addBookUseCase == null) {
            addBookUseCase = AddBookUseCase(
                repository = BookRepositoryProvider.provideRepository()
            )
        }
        return addBookUseCase!!
    }
}