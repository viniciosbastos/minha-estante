package com.aurelio.minhaestante.interactors

import com.aurelio.minhaestante.data.BookRepository
import com.aurelio.minhaestante.domain.Book
import com.aurelio.minhaestante.domain.BookState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoadShelvedBooksUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {

    suspend operator fun invoke(): Flow<List<Book>> = withContext(Dispatchers.IO) {
        bookRepository.getBooks(BookState.SHELF, null)
    }
}