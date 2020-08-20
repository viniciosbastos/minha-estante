package com.aurelio.minhaestante.data

import com.aurelio.minhaestante.domain.Book
import com.aurelio.minhaestante.domain.BookState
import com.aurelio.minhaestante.domain.Label
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepository @Inject constructor (private val bookDataSource: BookDataSource) {

    suspend fun addBook(book: Book) = bookDataSource.addBook(book)

    suspend fun getBooks(): Flow<List<Book>> = bookDataSource.getBooks()

    fun getBooks(state: BookState, label: Label?): Flow<List<Book>> = bookDataSource.getBooks(state, label)

    suspend fun addProgress(book: Book) = bookDataSource.addProgress(book)

    suspend fun deleteBook(book: Book) = bookDataSource.deleteBook(book)

    suspend fun updateBookState(book: Book, state: BookState) = bookDataSource.updateBookState(book.id, state)
}