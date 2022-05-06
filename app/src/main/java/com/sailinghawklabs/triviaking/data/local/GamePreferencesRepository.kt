package com.sailinghawklabs.triviaking.data.local

import androidx.datastore.core.DataStore
import com.sailinghawklabs.triviaking.domain.model.GamePreferences
import javax.inject.Inject

class GamePreferencesRepository @Inject constructor(
    private val dataStore: DataStore<GamePreferences>
) {



}