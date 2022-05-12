package com.example.domain.usecase.plays

import com.example.domain.repository.PlaysRepository
import com.example.domain.usecase.UseCase

class GetRecordUseCase(private val playsRepository: PlaysRepository): UseCase {
    suspend fun execute(): Int? = playsRepository.getRecord()
}