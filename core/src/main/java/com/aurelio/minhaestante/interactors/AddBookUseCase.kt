package com.aurelio.minhaestante.interactors

import com.aurelio.minhaestante.data.BookRepository
import com.aurelio.minhaestante.domain.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddBookUseCase @Inject constructor (private val bookRepository: BookRepository){

    suspend operator fun invoke(book: Book) {
        withContext(Dispatchers.IO) {
            bookRepository.addBook(book)
        }
    }
}