package com.brohit.show_phaze.data.data_source.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SessionEntity(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val token: String,
    val expiresAt: Long,
) {
    override fun toString(): String {
        return "$name=$token"
    }
}
