package com.sailinghawklabs.triviaking.data.mapper

import com.sailinghawklabs.triviaking.domain.model.CategoryStats
import com.sailinghawklabs.triviaking.domain.model.DIFFICULTY

private val DifficultyDisplayTable: HashMap<DIFFICULTY, String> = hashMapOf(
    (DIFFICULTY.ANY to "Any"),
    (DIFFICULTY.EASY to "Easy"),
    (DIFFICULTY.MEDIUM to "Medium"),
    (DIFFICULTY.HARD to "Hard"),

    )

private val DifficultyDtoTable: HashMap<DIFFICULTY, String?> = hashMapOf(
    (DIFFICULTY.ANY to null),
    (DIFFICULTY.EASY to "easy"),
    (DIFFICULTY.MEDIUM to "medium"),
    (DIFFICULTY.HARD to "hard"),
)

fun DIFFICULTY.toDtoString(): String? =
    DifficultyDtoTable.getValue(this)


fun DIFFICULTY.toDisplayString(): String {
    return DifficultyDisplayTable.getValue(this)
}

fun displayStringToDifficulty(value: String): DIFFICULTY {
    for (key in DifficultyDisplayTable.keys) {
        if (value == DifficultyDisplayTable.getValue(key))
            return key
    }
    throw Error("Unrecognized value for DIFFICULTY : $value")
}

fun dtoStringToDifficulty(value: String?): DIFFICULTY {
    for (key in DifficultyDisplayTable.keys) {
        if (value == DifficultyDtoTable.getValue(key))
            return key
    }
    throw Error("Unrecognized value for DIFFICULTY : $value")
}

fun DIFFICULTY.lookupCount(stats: CategoryStats?): Int? {
    if (stats != null) {
        if (this == DIFFICULTY.EASY) {
            return stats.numEasy
        } else if (this == DIFFICULTY.MEDIUM) {
            return stats.numMedium
        } else if (this == DIFFICULTY.HARD) {
            return stats.numHard
        } else if (this == DIFFICULTY.ANY) {
            return stats.numTotal
        }
    }
    return null
}

