package com.aurelio.minhaestante.data

import com.aurelio.minhaestante.domain.Label
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
class FilterDataSource @Inject constructor() {

    private var selectedLabel: Label? = null
    private val channel = ConflatedBroadcastChannel<Label?>().apply { offer(selectedLabel) }

    @FlowPreview
    fun getSelectedLabel(): Flow<Label?> = channel.asFlow()

    @Synchronized
    fun changeSelectedLabel(label: Label) {
        selectedLabel = label
        channel.offer(label)
    }
}