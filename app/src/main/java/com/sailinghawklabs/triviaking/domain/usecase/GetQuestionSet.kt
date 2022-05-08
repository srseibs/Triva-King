package com.sailinghawklabs.triviaking.domain.usecase

import com.sailinghawklabs.triviaking.domain.model.Question

interface GetQuestionSet {
    enum class DIFFICULTY {ANY, EASY, DIFFICULT, HARD}

    suspend operator fun invoke(
        numberOfQuestions: Int,
        categoryId: Int?,
        difficulty: DIFFICULTY = DIFFICULTY.ANY,
    ): Result<List<Question>>
}