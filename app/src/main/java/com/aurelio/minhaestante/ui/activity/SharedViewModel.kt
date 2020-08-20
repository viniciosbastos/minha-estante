package com.aurelio.minhaestante.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aurelio.minhaestante.data.FilterRepository
import com.aurelio.minhaestante.domain.Label
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedViewModel @Inject constructor(
    private val filterRepository: FilterRepository
): ViewModel() {

//    private val _label = MutableLiveData<Label>()
//    val label: LiveData<Label>
//        get() = _label

    fun changeChosenLabel(chosenLabel: Label) {
//        _label.value = chosenLabel
        filterRepository.changeSelectedFilter(chosenLabel)
    }
}