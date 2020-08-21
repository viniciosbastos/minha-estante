package com.aurelio.minhaestante.data

import com.aurelio.minhaestante.domain.Label
import kotlinx.coroutines.flow.Flow

interface LabelDataSource {

    suspend fun addLabel(label: Label)

    suspend fun getLabels(): Flow<List<Label>>

    suspend fun deleteLabel(label: Label)
}