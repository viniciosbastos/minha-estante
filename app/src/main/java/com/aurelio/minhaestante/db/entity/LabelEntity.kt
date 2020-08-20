package com.aurelio.minhaestante.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aurelio.minhaestante.domain.LabelColorEnum

@Entity(tableName = "label")
data class LabelEntity (
    @PrimaryKey
    var id: Int,
    var name: String,
    var colorName: LabelColorEnum
){

}