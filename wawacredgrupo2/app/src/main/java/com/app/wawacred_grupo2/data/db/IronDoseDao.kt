package com.app.wawacred_grupo2.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface IronDoseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ironDose: IronDose)

    @Query("SELECT * FROM iron_doses ORDER BY date DESC")
    fun getAll(): Flow<List<IronDose>>
}
