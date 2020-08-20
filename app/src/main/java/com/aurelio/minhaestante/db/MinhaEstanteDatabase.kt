package com.aurelio.minhaestante.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aurelio.minhaestante.db.entity.BookEntity
import com.aurelio.minhaestante.db.entity.LabelEntity

@Database(entities = [BookEntity::class, LabelEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MinhaEstanteDatabase : RoomDatabase(){

    abstract fun bookDao(): BookDao
    abstract fun labelDao(): LabelDao

    companion object {
        fun create(context: Context): MinhaEstanteDatabase {
            return Room.databaseBuilder(context, MinhaEstanteDatabase::class.java, "minha_estante.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}