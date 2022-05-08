package com.sailinghawklabs.triviaking.di.module

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.datastore.migrations.SharedPreferencesMigration
import com.sailinghawklabs.triviaking.data.local.GamePreferencesSerializer
import com.sailinghawklabs.triviaking.domain.model.GamePreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

private const val DATA_STORE_FILE_NAME = "game_prefs.data"

@InstallIn(SingletonComponent::class)
@Module
object DataStoreModule {

    @Singleton
    @Provides
    fun provideProtoDataStore(
        @ApplicationContext appContext: Context
    ): DataStore<GamePreferences> {
        return DataStoreFactory.create(
            serializer = GamePreferencesSerializer,
            produceFile = { appContext.dataStoreFile(DATA_STORE_FILE_NAME) },
            corruptionHandler = null,
            migrations = emptyList(),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
        )
    }
}
