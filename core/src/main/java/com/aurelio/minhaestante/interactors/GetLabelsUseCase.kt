package com.aurelio.minhaestante.interactors

import com.aurelio.minhaestante.data.LabelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetLabelsUseCase @Inject constructor(
    private val labelRepository: LabelRepository
) {

    suspend operator fun invoke() = withContext(Dispatchers.IO) {
        labelRepository.getLabels()
    }
}