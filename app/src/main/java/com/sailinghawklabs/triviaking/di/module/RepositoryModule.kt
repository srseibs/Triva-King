package com.sailinghawklabs.triviaking.di.module

import androidx.datastore.core.DataStore
import com.sailinghawklabs.triviaking.data.repository.PreferencesRepositoryImpl
import com.sailinghawklabs.triviaking.data.remote.OpenTriviaDatabaseApi
import com.sailinghawklabs.triviaking.data.repository.QuizRepositoryImpl
import com.sailinghawklabs.triviaking.domain.model.GamePreferences
import com.sailinghawklabs.triviaking.domain.repository.PreferencesRepository
import com.sailinghawklabs.triviaking.domain.repository.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule{


    @Provides
    @Singleton
    fun providesTriviaApiRepository(
        api: OpenTriviaDatabaseApi,
    ): QuizRepository {
        return QuizRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesGamePreferencesRepository(
        dataStore: DataStore<GamePreferences>,
    ): PreferencesRepository {
        return PreferencesRepositoryImpl(dataStore)
    }

}