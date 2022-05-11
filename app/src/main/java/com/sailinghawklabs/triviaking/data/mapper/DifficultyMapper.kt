package com.sailinghawklabs.triviaking.data.mapper

import com.sailinghawklabs.triviaking.domain.model.DIFFICULTY

private val DifficultyDisplayTable: HashMap<DIFFICULTY, String> = hashMapOf(
    (DIFFICULTY.ANY to "Any"),
    (DIFFICULTY.EASY to "Easy"),
    (DIFFICULTY.HARD to "Hard"),
    (DIFFICULTY.DIFFICULT to "Difficult")
)

private val DifficultyDtoTable: HashMap<DIFFICULTY, String?> = hashMapOf(
    (DIFFICULTY.ANY to null),
    (DIFFICULTY.EASY to "easy"),
    (DIFFICULTY.HARD to "hard"),
    (DIFFICULTY.DIFFICULT to "difficult")
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

