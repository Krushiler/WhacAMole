package com.example.whacamole.di

import com.example.domain.game.GameManager
import com.example.domain.usecase.game.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GameModule {

    @Provides
    @Singleton
    fun provideGameManager() = GameManager()

    @Provides
    @Singleton
    fun provideStartGameUseCase(gameManager: GameManager) = StartGameUseCase(gameManager)


    @Provides
    @Singleton
    fun provideGetGameEventUseCase(gameManager: GameManager) = GetGameEventUseCase(gameManager)

    @Provides
    @Singleton
    fun provideWhacUseCase(gameManager: GameManager) = WhacUseCase(gameManager)

    @Provides
    @Singleton
    fun provideGetScoreUseCase(gameManager: GameManager) = GetScoreUseCase(gameManager)

    @Provides
    @Singleton
    fun provideGetGameStateUseCase(gameManager: GameManager) = GetGameStateUseCase(gameManager)


}