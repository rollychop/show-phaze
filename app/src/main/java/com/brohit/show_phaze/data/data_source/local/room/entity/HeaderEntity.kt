package com.brohit.show_phaze.data.data_source.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "header_table")
data class HeaderEntity(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val value: String
)
