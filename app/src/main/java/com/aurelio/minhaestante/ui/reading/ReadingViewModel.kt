package com.aurelio.minhaestante.ui.reading

import androidx.lifecycle.*
import com.aurelio.minhaestante.data.FilterRepository
import com.aurelio.minhaestante.domain.Book
import com.aurelio.minhaestante.domain.Label
import com.aurelio.minhaestante.interactors.*
import com.aurelio.minhaestante.util.Event
import kotlinx.coroutines.launch
import javax.inject.Inject

class ReadingViewModel @Inject constructor(
    private val loadReadingBooksUseCase: LoadReadingBooksUseCase,
    private val addProgressUseCase: AddProgressUseCase,
    private val deleteBookUseCase: DeleteBookUseCase,
    private val shelfBookUseCase: ShelfBookUseCase,
    filterRepository: FilterRepository
): ViewModel() {

    private val _filter: LiveData<Label?> = filterRepository.getSelectedFilter().asLiveData()

    private val  _navigateToChooseLabelDialog = MutableLiveData<Event<Unit>>()
    val navigateToChooseLabelDialog: LiveData<Event<Unit>> = _navigateToChooseLabelDialog

    private var _editingBook: Book? = null

    val books: LiveData<List<Book>> = _filter.switchMap {label ->
        liveData<List<Book>> {
            val books = loadReadingBooksUseCase(label)
            emitSource(books.asLiveData())
        }
    }

    fun addProgress(position: Int, progress: Int) {
        viewModelScope.launch {
            val book = books.value?.get(position)?.copy()
            book?.let{
                book.progress = progress
                addProgressUseCase(book)
            }
        }
    }

    fun removeRook(position: Int) {
        viewModelScope.launch {
            val book = books.value?.get(position)
            book?.let { deleteBookUseCase(it) }
        }
    }

    fun shelfBook(position: Int) {
        viewModelScope.launch {
            val book = books.value?.get(position)
            book?.let { shelfBookUseCase(it) }
        }
    }

    fun setBookLabel(book: Book) {
        _editingBook = book
        _navigateToChooseLabelDialog.value = Event(Unit)
    }
}