package com.example.domain.usecase.plays

import com.example.domain.repository.PlaysRepository
import com.example.domain.usecase.UseCase

class SaveRecordUseCase(private val playsRepository: PlaysRepository): UseCase {
    suspend fun execute(score: Int) = playsRepository.insertRecord(score)
}