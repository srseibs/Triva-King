package com.sailinghawklabs.triviaking.data.mapper

import com.sailinghawklabs.triviaking.domain.model.DIFFICULTY

private val DifficultyDtoTable: HashMap<DIFFICULTY, String?> = hashMapOf(
    (DIFFICULTY.ANY to null),
    (DIFFICULTY.EASY to "Easy"),
    (DIFFICULTY.HARD to "Hard"),
    (DIFFICULTY.DIFFICULT to "Difficult")
)

fun DIFFICULTY.toDtoString(): String? = DifficultyDtoTable[this]

fun DIFFICULTY.toDisplayString(): String {
    return DifficultyDtoTable[this] ?: "Any"
}

fun stringToDifficulty(value: String?): DIFFICULTY {
    for (key in DifficultyDtoTable.keys) {
        if (value == DifficultyDtoTable[key])
            return key
    }
    throw Error("unrecognized value for DIFFICULTY")
}
