package com.aurelio.minhaestante.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aurelio.minhaestante.db.entity.LabelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LabelDao {

    @Insert
    suspend fun insert(label: LabelEntity)

    @Query("select * from label")
    fun getLabels(): Flow<List<LabelEntity>>

    @Query("select max(id) from label")
    suspend fun getLastId(): Int?
}