package com.aurelio.minhaestante.util

import com.aurelio.minhaestante.R
import com.aurelio.minhaestante.domain.LabelColorEnum

fun LabelColorEnum.getColor(): Int = when (this) {
    LabelColorEnum.RED -> R.color.red
    LabelColorEnum.GREEN -> R.color.green
    LabelColorEnum.BLUE -> R.color.blue
    LabelColorEnum.YELLOW -> R.color.yellow
    LabelColorEnum.LIGHTBLUE -> R.color.light_blue
    else -> android.R.color.transparent
}