package com.sailinghawklabs.triviaking.data.local


import androidx.datastore.core.Serializer
import com.sailinghawklabs.triviaking.domain.model.GamePreferences
import com.sailinghawklabs.triviaking.domain.model.defaultGamePreferences
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
object GamePreferencesSerializer: Serializer<GamePreferences> {
    override val defaultValue: GamePreferences
        get() = defaultGamePreferences

    override suspend fun readFrom(input: InputStream): GamePreferences {
        return try {
            Json.decodeFromString(
                deserializer = GamePreferences.serializer(),
                string = input.readBytes().decodeToString()
            )
        }catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: GamePreferences, output: OutputStream) {
        output.write(
            Json.encodeToString(
                GamePreferences.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}