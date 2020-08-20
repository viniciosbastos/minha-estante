package com.aurelio.minhaestante.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aurelio.minhaestante.domain.BookState
import java.util.*

@Entity(tableName = "book")
data class BookEntity (
    @PrimaryKey
    val id: Int,
    val title: String,
    val author: String,
    val pages: Int,
    val progress: Int,
    val bookState: BookState = BookState.READING,
    val addedAt: Date,
    val labelId: Int? = null
)