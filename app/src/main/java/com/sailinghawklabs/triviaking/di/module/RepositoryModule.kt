package com.sailinghawklabs.triviaking.di.module

import androidx.datastore.core.DataStore
import com.sailinghawklabs.triviaking.data.local.GamePreferencesRepository
import com.sailinghawklabs.triviaking.domain.model.GamePreferences
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
    fun providesGamePreferencesRepository(
        dataStore: DataStore<GamePreferences>,
    ): GamePreferencesRepository {
        return GamePreferencesRepository(dataStore)
    }

}