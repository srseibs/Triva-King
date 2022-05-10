package com.sailinghawklabs.triviaking.data.mapper

import com.sailinghawklabs.triviaking.data.remote.dto.QuestionDto
import com.sailinghawklabs.triviaking.domain.model.DIFFICULTY
import com.sailinghawklabs.triviaking.domain.model.Question

fun QuestionDto.toQuestion(): Question {
    return Question(
        category = category,
        question = question,
        difficulty = stringToDifficulty(difficultyString),
        correctAnswer = correctAnswer,
        answers = incorrectAnswers
    )
}
