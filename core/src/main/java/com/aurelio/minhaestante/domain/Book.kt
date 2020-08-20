package com.aurelio.minhaestante.domain

import java.util.*

data class Book (
    var id: Int,
    var title: String,
    var author: String,
    var pages: Int,
    var progress: Int = 0,
    var addedAt: Date = Date(),
    var label: Label? = null
)

fun Book.progressPercentage(): Int = this.progress*100/this.pages