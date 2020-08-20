package com.aurelio.minhaestante.source

import com.aurelio.minhaestante.db.LabelDao
import com.aurelio.minhaestante.db.entity.LabelEntity
import com.aurelio.minhaestante.domain.Label
import com.aurelio.minhaestante.data.LabelDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomLabelDataSource @Inject constructor(
    private val labelDao: LabelDao
) : LabelDataSource {

    override suspend fun addLabel(label: Label) {
        val lastId = labelDao.getLastId()
        labelDao.insert(LabelEntity(
            id = if (lastId == null) 1 else lastId + 1,
            name = label.name,
            colorName = label.color
        ))
    }

    override suspend fun getLabels(): Flow<List<Label>> = labelDao.getLabels().map {
        it.map { label ->
            Label(
                id = label.id,
                name = label.name,
                color = label.colorName
            )
        }}
}