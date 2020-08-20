package com.aurelio.minhaestante.db

import androidx.room.*
import com.aurelio.minhaestante.db.entity.BookEntity
import com.aurelio.minhaestante.db.entity.BookWithLabelRelation
import com.aurelio.minhaestante.domain.BookState
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Insert
    suspend fun addBook(bookEntity: BookEntity)

    @Query("select max(id) from book")
    suspend fun getLastId(): Int?

    @Query("select * from book")
    fun getBooks(): Flow<List<BookEntity>>

    @Query("select * from book where bookState = :state")
    fun getBooks(state: BookState): Flow<List<BookWithLabelRelation>>

    @Query("select * from book where bookState = :state and labelId = :labelId")
    fun getBooks(state: BookState, labelId: Int): Flow<List<BookWithLabelRelation>>

    @Update
    suspend fun updateBook(book: BookEntity)

    @Delete
    suspend fun deleteBook(book: BookEntity)

    @Query("update book set bookState = :state where id = :id")
    fun updateBookState(id: Int, state: BookState)
}