package com.example.data.local.plays

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlayEntity (
    @PrimaryKey val name: String,
    val score: Int
)