package com.aurelio.minhaestante.data

import com.aurelio.minhaestante.data.LabelDataSource
import com.aurelio.minhaestante.domain.Label
import javax.inject.Inject

class LabelRepository @Inject constructor(
    private val labelDataSource: LabelDataSource
) {

    suspend fun addLabel(label: Label) = labelDataSource.addLabel(label)

    suspend fun getLabels() = labelDataSource.getLabels()

}