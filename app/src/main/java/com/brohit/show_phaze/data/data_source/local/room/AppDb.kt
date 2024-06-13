package com.brohit.show_phaze.data.data_source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.brohit.show_phaze.data.data_source.local.room.dao.HeaderDao
import com.brohit.show_phaze.data.data_source.local.room.dao.SessionDao
import com.brohit.show_phaze.data.data_source.local.room.entity.HeaderEntity
import com.brohit.show_phaze.data.data_source.local.room.entity.SessionEntity

@Database(
    version = 1,
    entities = [SessionEntity::class, HeaderEntity::class],
    exportSchema = true
)
abstract class AppDb : RoomDatabase() {
    abstract fun sessionDao(): SessionDao
    abstract fun headerDao(): HeaderDao
}