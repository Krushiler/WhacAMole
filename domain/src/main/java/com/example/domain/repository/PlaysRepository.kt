package com.example.domain.repository

interface PlaysRepository {
    suspend fun insertRecord(score: Int)
    suspend fun getRecord(): Int?
}