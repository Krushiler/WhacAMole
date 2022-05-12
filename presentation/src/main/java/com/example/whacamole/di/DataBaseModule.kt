package com.example.whacamole.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.plays.PlayDao
import com.example.data.local.plays.PlaysDatabase
import com.example.data.local.repository.PlaysRepositoryImpl
import com.example.domain.repository.PlaysRepository
import com.example.domain.usecase.plays.GetRecordUseCase
import com.example.domain.usecase.plays.SaveRecordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun providePlaysDataBase(@ApplicationContext appContext: Context): PlaysDatabase {
        return Room.databaseBuilder(
            appContext,
            PlaysDatabase::class.java,
            "PlaysDatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun providePlayDao(database: PlaysDatabase): PlayDao {
        return database.playDao
    }

    @Provides
    @Singleton
    fun providePlaysRepository(database: PlaysDatabase): PlaysRepository = PlaysRepositoryImpl(database)

    @Provides
    @Singleton
    fun provideSaveRecordUseCase(repository: PlaysRepository): SaveRecordUseCase = SaveRecordUseCase(repository)

    @Provides
    @Singleton
    fun provideGetRecordUseCase(repository: PlaysRepository): GetRecordUseCase = GetRecordUseCase(repository)

}