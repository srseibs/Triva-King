package com.sailinghawklabs.triviaking.presentation.quiz

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sailinghawklabs.triviaking.data.mapper.toDisplayString
import com.sailinghawklabs.triviaking.domain.model.Question
import com.sailinghawklabs.triviaking.domain.model.defaultGamePreferences
import com.sailinghawklabs.triviaking.domain.repository.PreferencesRepository
import com.sailinghawklabs.triviaking.domain.repository.QuizRepository
import com.sailinghawklabs.triviaking.ui.theme.util.TriBoxState
import com.sailinghawklabs.triviaking.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizRepository: QuizRepository,
    private val preferencesRepository: PreferencesRepository,
) : ViewModel() {

    var screenState by mutableStateOf(QuizScreenState())
        private set

    private var quizState by mutableStateOf(QuizOverallState())

    var gamePreferencesState by mutableStateOf(defaultGamePreferences)

    init {
        viewModelScope.launch {
            fetchPreferences()
            fetchAllQuestions()
            resetQuiz()
        }
    }

    fun onQuizEvent(event: QuizScreenEvent) {

        when(event) {
            is QuizScreenEvent.AnswerPressed -> {
                processAnswer(event.answerIndex)
            }
            is QuizScreenEvent.NextQuestionPressed -> {
            }
        }
    }

    private fun processAnswer(answer: Int) {

        val newAnswers = ArrayList<TriBoxState>()
        for (i in 0 until screenState.answerState.size){
            val newBox = if (answer == i)
                TriBoxState.CORRECT
            else
                TriBoxState.WRONG
            newAnswers.add(newBox)
        }
        screenState = screenState.copy(
            answerState = newAnswers
        )

        Log.d(
            "QuizViewModel",
            "processAnswer: screenState:answerState = ${screenState.answerState.joinToString()}"
        )

    }

    private fun resetQuiz() {
        quizState = quizState.copy(
            numCorrect = 0,
            numWrong = 0,
            currentQuestionNumber = 0,
        )
        setupQuestion(0)
    }

    private fun setupQuestion(questionNum: Int) {
        quizState = quizState.copy(
            currentQuestionNumber = questionNum,
        )

        val currentQuestion = quizState.quiz[questionNum]
        val numQuestions = quizState.quiz.size

        screenState = prepareQuestion(currentQuestion, numQuestions)
    }

    private suspend fun fetchPreferences() {
        try {
            gamePreferencesState = preferencesRepository.getAllPreferences()
            Log.d("QuizViewModel", "fetchPreferences: success: ${gamePreferencesState}")
        } catch (e: Exception) {
            Log.d("QuizViewModel", "fetchPreferences: ${e.localizedMessage}")
        }
    }

    private suspend fun fetchAllQuestions() {
        try {
            quizRepository.fetchQuestionSet(
                numberOfQuestions = gamePreferencesState.numberOfQuestions,
                category = gamePreferencesState.category,
                difficulty = gamePreferencesState.difficulty
            ).collect() { result ->
                when (result) {
                    is Result.Success -> {
                        Log.d("QuizViewModel", "fetchAllQuestions: Success: ${result.data}")

                        quizState = quizState.copy(
                            quiz = result.data!!
                        )
                    }
                    is Result.Loading -> {
                        Log.d("QuizViewModel", "fetchAllQuestions: loading")
                    }
                    is Result.Error -> {
                        Log.d("QuizViewModel", "fetchAllQuestions: ERROR: ${result.message}")
                    }
                }
            }
        } catch (e: Exception) {
            Log.d("QuizViewModel", "fetchAllQuestions: ${e.localizedMessage}")
        }
    }


    private fun prepareQuestion(question: Question, numQuestions: Int): QuizScreenState {
        val incorrectAnswers = question.answers
        val numAnswers = incorrectAnswers.size + 1
        val correctAnswerIndex = Random.nextInt(numAnswers)
        val answers = question.answers.shuffled().toMutableList()
        answers.add(correctAnswerIndex, question.correctAnswer)
        val answerState = List(numAnswers) { TriBoxState.UNCHECKED }

        return screenState.copy(
            category = question.category,
            questionNumber = 0,
            question = question.question,
            numberOfQuestions = numQuestions,
            numCorrect = 0,
            difficulty = question.difficulty.toDisplayString(),
            answers = answers,
            correctAnswerIndex = correctAnswerIndex,
            answerState = answerState
        )
    }
}
