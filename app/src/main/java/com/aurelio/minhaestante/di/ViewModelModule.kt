package com.aurelio.minhaestante.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aurelio.minhaestante.ui.activity.MainViewModel
import com.aurelio.minhaestante.ui.activity.SharedViewModel
import com.aurelio.minhaestante.ui.addBook.AddBookViewModel
import com.aurelio.minhaestante.ui.reading.ChooseLabelViewModel
import com.aurelio.minhaestante.ui.reading.ReadingViewModel
import com.aurelio.minhaestante.ui.shelf.ShelfViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindsViewModelProviderFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ReadingViewModel::class)
    abstract fun bindReadingViewModel(viewModel: ReadingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ShelfViewModel::class)
    abstract fun bindLibraryViewModel(viewModel: ShelfViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddBookViewModel::class)
    abstract fun bindAddBookViewModel(viewModel: AddBookViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SharedViewModel::class)
    abstract fun bindSharedViewModel(viewModel: SharedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChooseLabelViewModel::class)
    abstract fun bindChooseLabelViewModel(viewModel: ChooseLabelViewModel): ViewModel
}