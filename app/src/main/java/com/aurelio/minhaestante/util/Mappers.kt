package com.aurelio.minhaestante.util

import com.aurelio.minhaestante.db.entity.LabelEntity
import com.aurelio.minhaestante.domain.Label

fun LabelEntity.mapToDomain(): Label = Label(
    id = this.id,
    name = this.name,
    color = this.colorName
)