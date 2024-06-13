package com.brohit.show_phaze.data.data_source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.brohit.show_phaze.data.data_source.local.room.entity.HeaderEntity

@Dao
interface HeaderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeader(vararg headers: HeaderEntity)

    @Query("SELECT * FROM header_table")
    suspend fun getAllHeaders(): List<HeaderEntity>

    @Query("DELETE FROM header_table")
    suspend fun clearAll()
}