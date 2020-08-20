package com.aurelio.minhaestante.ui.addBook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aurelio.minhaestante.domain.Book
import com.aurelio.minhaestante.interactors.AddBookUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddBookViewModel @Inject constructor(
    private val addBookUseCase: AddBookUseCase
): ViewModel() {

    fun addBook(book: Book) {
        viewModelScope.launch {
            addBookUseCase(book)
        }
    }
}