package com.aurelio.minhaestante.di

import android.content.Context
import com.aurelio.minhaestante.data.BookDataSource
import com.aurelio.minhaestante.db.BookDao
import com.aurelio.minhaestante.db.LabelDao
import com.aurelio.minhaestante.db.MinhaEstanteDatabase
import com.aurelio.minhaestante.data.LabelDataSource
import com.aurelio.minhaestante.source.RoomBookDataSource
import com.aurelio.minhaestante.source.RoomLabelDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class DatabaseModule {

    companion object {
        @Provides
        @Singleton
        fun provideDatabase(context: Context): MinhaEstanteDatabase = MinhaEstanteDatabase.create(context)

        @Provides
        fun provideBookDao(db: MinhaEstanteDatabase): BookDao = db.bookDao()

        @Provides
        fun provideLabelDao(db: MinhaEstanteDatabase): LabelDao = db.labelDao()
    }

    @Binds
    abstract fun bindBookDataSource(bookDataSource: RoomBookDataSource): BookDataSource

    @Binds
    abstract fun bindLabelDataSource(labelDataSource: RoomLabelDataSource): LabelDataSource
}