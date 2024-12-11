package com.example.libraryapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.libraryapp.domain.model.Book
import com.example.libraryapp.domain.usecase.UseCaseProvider
import kotlinx.coroutines.launch

class BookDetailViewModel: ViewModel() {
    private val getBookByIdUseCase = UseCaseProvider.provideGetBookByIdUseCase()

    private val _book = MutableLiveData<Book?>()
    val book: MutableLiveData<Book?> = _book

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun loadBook(id: Int): MutableLiveData<Book?> {
        val bookLiveData = MutableLiveData<Book?>()
        viewModelScope.launch {
            _loading.value = true
            try {
                bookLiveData.value = getBookByIdUseCase(id)
            } finally {
                _loading.value = false
            }
        }
        return bookLiveData
    }
}