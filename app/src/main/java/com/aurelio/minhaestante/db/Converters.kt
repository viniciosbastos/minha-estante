package com.aurelio.minhaestante.db

import androidx.room.TypeConverter
import com.aurelio.minhaestante.domain.BookState
import com.aurelio.minhaestante.domain.LabelColorEnum
import java.util.*

class Converters {

    @TypeConverter
    fun fromState(state: BookState): String = state.name

    @TypeConverter
    fun toState(name: String): BookState = BookState.valueOf(name)

    @TypeConverter
    fun fromDate(date: Date): Long = date.time

    @TypeConverter
    fun toDate(millis: Long): Date = Date(millis)

    @TypeConverter
    fun fromColorEnum(color: LabelColorEnum): String = color.name

    @TypeConverter
    fun toColorEnum(name: String): LabelColorEnum = LabelColorEnum.valueOf(name)
}