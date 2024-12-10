package com.example.libraryapp.presentation.viewmodel

class BookDetailViewModel {
    private val getBookByIdUseCase: GetBookByIdUseCase
    private val _book = MutableLiveData<Book>()
    val book: LiveData<Book> = _book
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    fun loadBook(id: Int) {
        viewModelScope.launch {
            _loading.value = true
            try {
                _book.value = getBookByIdUseCase(id)
            } finally {
                _loading.value = false
            }
        }
    }
}