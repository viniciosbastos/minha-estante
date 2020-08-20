package com.aurelio.minhaestante.data

import com.aurelio.minhaestante.domain.Book
import com.aurelio.minhaestante.domain.BookState
import com.aurelio.minhaestante.domain.Label
import kotlinx.coroutines.flow.Flow

interface BookDataSource {

    suspend fun addBook(book: Book)

    suspend fun getBooks(): Flow<List<Book>>

    fun getBooks(state: BookState, label: Label?): Flow<List<Book>>

    suspend fun addProgress(book: Book)

    suspend fun deleteBook(book: Book)

    suspend fun updateBookState(id: Int, state: BookState)
}