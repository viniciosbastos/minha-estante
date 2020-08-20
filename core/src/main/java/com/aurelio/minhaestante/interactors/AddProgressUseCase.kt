package com.aurelio.minhaestante.interactors

import com.aurelio.minhaestante.data.BookRepository
import com.aurelio.minhaestante.domain.Book
import javax.inject.Inject

class AddProgressUseCase @Inject constructor (
    private val bookRepository: BookRepository
) {

    suspend operator fun invoke(book: Book) = bookRepository.addProgress(book)
}