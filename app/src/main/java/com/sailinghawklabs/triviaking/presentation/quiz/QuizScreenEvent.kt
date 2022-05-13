package com.sailinghawklabs.triviaking.presentation.quiz

import com.sailinghawklabs.triviaking.domain.model.DIFFICULTY

sealed class QuizScreenEvent{
    data class AnswerPressed(val answerIndex: Int): QuizScreenEvent()
    object GetNextQuestion: QuizScreenEvent()
    object RetakeThisQuiz: QuizScreenEvent()
    object CreateNewQuiz: QuizScreenEvent()
}