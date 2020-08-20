package com.aurelio.minhaestante.source

import com.aurelio.minhaestante.data.BookDataSource
import com.aurelio.minhaestante.db.BookDao
import com.aurelio.minhaestante.db.entity.BookEntity
import com.aurelio.minhaestante.domain.Book
import com.aurelio.minhaestante.domain.BookState
import com.aurelio.minhaestante.domain.Label
import com.aurelio.minhaestante.util.mapToDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomBookDataSource @Inject constructor(private val bookDao: BookDao): BookDataSource{
    override suspend fun addBook(book: Book) {
        withContext(Dispatchers.IO) {
            val lastId = bookDao.getLastId()
            bookDao.addBook(BookEntity(
                id = if (lastId == null) 1 else lastId + 1,
                title = book.title,
                author = book.author,
                pages = book.pages,
                progress = book.progress,
                addedAt = book.addedAt
            ))
        }
    }

    override suspend fun getBooks(): Flow<List<Book>> = bookDao.getBooks().map {
        it.map { book -> Book(
            id = book.id,
            title = book.title,
            author = book.author,
            pages = book.pages,
            progress = book.progress,
            addedAt = book.addedAt
        )}
    }

    override fun getBooks(state: BookState, label: Label?): Flow<List<Book>> {
        val books= if (label == null) bookDao.getBooks(state) else bookDao.getBooks(state, label.id)
        return books.map {
            it.map { bookWithLabel -> Book(
                id = bookWithLabel.book.id,
                title = bookWithLabel.book.title,
                author = bookWithLabel.book.author,
                pages = bookWithLabel.book.pages,
                progress = bookWithLabel.book.progress,
                addedAt = bookWithLabel.book.addedAt,
                label = bookWithLabel.label?.mapToDomain()
            )}
        }
    }

    override suspend fun addProgress(book: Book) = bookDao.updateBook(BookEntity(
        id = book.id,
        title = book.title,
        author = book.author,
        pages = book.pages,
        progress = book.progress,
        addedAt = book.addedAt
    ))

    override suspend fun deleteBook(book: Book) = bookDao.deleteBook(BookEntity(
        id = book.id,
        title = book.title,
        author = book.author,
        pages = book.pages,
        progress = book.progress,
        addedAt = book.addedAt
    ))

    override suspend fun updateBookState(id: Int, state: BookState) = bookDao.updateBookState(id, state)
}