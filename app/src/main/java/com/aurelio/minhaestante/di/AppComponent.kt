package com.aurelio.minhaestante.di

import android.content.Context
import com.aurelio.minhaestante.ui.activity.MainActivity
import com.aurelio.minhaestante.ui.addBook.AddBookFragment
import com.aurelio.minhaestante.ui.reading.ChooseLabelDialog
import com.aurelio.minhaestante.ui.reading.ReadingFragment
import com.aurelio.minhaestante.ui.shelf.ShelfFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ViewModelModule::class, DatabaseModule::class])
@Singleton
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: ReadingFragment)
    fun inject(fragment: ShelfFragment)
    fun inject(fragment: AddBookFragment)
    fun inject(fragment: ChooseLabelDialog)
    fun inject(activity: MainActivity)
}