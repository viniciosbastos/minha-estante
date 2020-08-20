package com.aurelio.minhaestante.interactors

import com.aurelio.minhaestante.data.LabelRepository
import com.aurelio.minhaestante.domain.Label
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddLabelUseCase @Inject constructor(
    private val labelRepository: LabelRepository
) {

    suspend operator fun invoke(label: Label) = withContext(Dispatchers.IO) {
        labelRepository.addLabel(label)
    }
}