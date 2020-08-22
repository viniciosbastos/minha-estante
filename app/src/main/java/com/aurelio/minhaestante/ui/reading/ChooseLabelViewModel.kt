package com.aurelio.minhaestante.ui.reading

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.aurelio.minhaestante.domain.Label
import com.aurelio.minhaestante.interactors.GetLabelsUseCase
import javax.inject.Inject

class ChooseLabelViewModel @Inject constructor(
    private val getLabelsUseCase: GetLabelsUseCase
): ViewModel() {

    val labels: LiveData<List<Label>> = liveData {
        val labels = getLabelsUseCase()
        emitSource(labels.asLiveData())
    }
}