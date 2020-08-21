package com.aurelio.minhaestante.ui.activity

import androidx.lifecycle.*
import com.aurelio.minhaestante.interactors.AddLabelUseCase
import com.aurelio.minhaestante.interactors.GetLabelsUseCase
import com.aurelio.minhaestante.domain.Label
import com.aurelio.minhaestante.interactors.DeleteLabelUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val addLabelUseCase: AddLabelUseCase,
    private val getLabelsUseCase: GetLabelsUseCase,
    private val deleteLabelUseCase: DeleteLabelUseCase
) : ViewModel() {

    val labels: LiveData<List<Label>> = liveData {
        emitSource(getLabelsUseCase().asLiveData())
    }

    fun addLabel(label: Label) {
        viewModelScope.launch {
            addLabelUseCase(label)
        }
    }

    fun deleteLabel(label: Label) {
        viewModelScope.launch {
            deleteLabelUseCase(label)
        }
    }

}