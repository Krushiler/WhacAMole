package com.example.data.local.repository

import androidx.room.withTransaction
import com.example.data.local.plays.NAME_RECORD
import com.example.data.local.plays.PlayEntity
import com.example.data.local.plays.PlaysDatabase
import com.example.domain.repository.PlaysRepository
import kotlinx.coroutines.coroutineScope

class PlaysRepositoryImpl(private val dataBase: PlaysDatabase): PlaysRepository {

    override suspend fun insertRecord(score: Int) {
        dataBase.withTransaction {
            val oldRecord = dataBase.playDao.getPlayByName(NAME_RECORD)
            if (oldRecord == null || score > oldRecord.score) {
                dataBase.playDao.createPlay(PlayEntity(NAME_RECORD, score))
            }
        }
    }

    override suspend fun getRecord(): Int? = coroutineScope {
        dataBase.playDao.getPlayByName(NAME_RECORD)?.score
    }

}