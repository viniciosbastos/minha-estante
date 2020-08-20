package com.aurelio.minhaestante.ui.shelf

import androidx.lifecycle.*
import com.aurelio.minhaestante.domain.Book
import com.aurelio.minhaestante.interactors.DeleteBookUseCase
import com.aurelio.minhaestante.interactors.LoadShelvedBooksUseCase
import com.aurelio.minhaestante.interactors.ReadBookUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShelfViewModel @Inject constructor(
    private val loadShelvedBooksUseCase: LoadShelvedBooksUseCase,
    private val readBookUseCase: ReadBookUseCase,
    private val deleteBookUseCase: DeleteBookUseCase
): ViewModel() {

    val books: LiveData<List<Book>> = liveData {
        delay(1000)
        emitSource(loadShelvedBooksUseCase().asLiveData())
    }

    fun readBook(position: Int) {
        viewModelScope.launch {
            val book = books.value?.get(position)
            book?.let { readBookUseCase(book) }
        }
    }

    fun deleteBook(position: Int) {
        viewModelScope.launch {
            val book = books.value?.get(position)
            book?.let { deleteBookUseCase(it) }
        }
    }
}