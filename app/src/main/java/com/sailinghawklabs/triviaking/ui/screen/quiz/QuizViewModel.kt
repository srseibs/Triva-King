package com.sailinghawklabs.triviaking.ui.screen.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sailinghawklabs.triviaking.data.remote.OpenTriviaDatabaseApi
import com.sailinghawklabs.triviaking.data.remote.dto.QuestionDto
import com.sailinghawklabs.triviaking.data.remote.dto.ResponseCode
import com.sailinghawklabs.triviaking.ui.theme.util.TriBoxState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class QuizViewModel @Inject constructor(
    // need to use repository
    private val api: OpenTriviaDatabaseApi,
) : ViewModel() {

    private val _viewState = MutableStateFlow(QuizScreenState())
    val viewState = _viewState.asStateFlow()

    init {
        fetchQuestions()
    }


    private fun fetchQuestions() {
        viewModelScope.launch {
            val questionResponse = api.getQuestions(
                quantity = 5,
                categoryId = null,
            )

            if (questionResponse.responseCode == ResponseCode.SUCCESS) {
                val question = questionResponse.results[0] // for now
                _viewState.value = prepareQuestion(question)
            }
        }

    }


    private fun prepareQuestion(question: QuestionDto): QuizScreenState {
        val incorrectAnswers = question.incorrectAnswers
        val numAnswers = incorrectAnswers.size + 1
        val correctAnswerIndex = Random.nextInt(numAnswers)
        val answers = question.incorrectAnswers.shuffled().toMutableList()
        answers.add(correctAnswerIndex, question.correctAnswer)
        val answerState = List(numAnswers) { TriBoxState.UNCHECKED }

        return _viewState.value.copy(
            category = question.category,
            questionNumber = 0,
            question = question.question,
            numberOfQuestions = 5,
            numCorrect = 0,
            difficulty = question.difficulty,
            answers = answers,
            correctAnswer = correctAnswerIndex,
            answerState = answerState
        )
    }
}