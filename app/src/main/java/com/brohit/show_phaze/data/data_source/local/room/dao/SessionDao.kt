package com.brohit.show_phaze.data.data_source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.brohit.show_phaze.data.data_source.local.room.entity.SessionEntity

@Dao
interface SessionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSessionToken(vararg tokens: SessionEntity)

    @Query("SELECT * FROM sessionentity")
    suspend fun getTokens(): List<SessionEntity>

    @Query("DELETE FROM sessionentity")
    suspend fun clearAll()
}