package com.aurelio.minhaestante.interactors

import com.aurelio.minhaestante.data.BookRepository
import com.aurelio.minhaestante.domain.Book
import com.aurelio.minhaestante.domain.BookState
import com.aurelio.minhaestante.domain.Label
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoadReadingBooksUseCase @Inject constructor (
    private val bookRepository: BookRepository
) {

    suspend operator fun invoke(filter: Label?): Flow<List<Book>> = withContext(Dispatchers.IO) {
        bookRepository.getBooks(BookState.READING, filter)
    }
}