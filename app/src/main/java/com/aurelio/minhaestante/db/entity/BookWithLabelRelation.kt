package com.aurelio.minhaestante.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class BookWithLabelRelation (
    @Embedded val book: BookEntity,
    @Relation(
        parentColumn = "labelId",
        entityColumn = "id"
    )
    val label: LabelEntity?
){

}