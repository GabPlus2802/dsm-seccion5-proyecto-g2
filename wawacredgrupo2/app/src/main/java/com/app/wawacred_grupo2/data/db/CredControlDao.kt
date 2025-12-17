package com.app.wawacred_grupo2.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CredControlDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(credControl: CredControl)

    @Query("SELECT * FROM cred_controls ORDER BY date DESC")
    fun getAll(): Flow<List<CredControl>>
}
