package com.example.data.local.plays

import androidx.room.*

@Dao
interface PlayDao {
    @Query("SELECT * FROM playentity WHERE name=:name LIMIT 1")
    suspend fun getPlayByName(name: String): PlayEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createPlay(play: PlayEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(play: PlayEntity): Int
}