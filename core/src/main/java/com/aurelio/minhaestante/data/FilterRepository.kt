package com.aurelio.minhaestante.data

import com.aurelio.minhaestante.domain.Label
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilterRepository @Inject constructor(
    private val filterDataSource: FilterDataSource
) {

    fun getSelectedFilter(): Flow<Label?> = filterDataSource.getSelectedLabel()

    fun changeSelectedFilter(label: Label) = filterDataSource.changeSelectedLabel(label)
}