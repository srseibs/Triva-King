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

    private var gameState by mutableStateOf(QuizGameState())

    var gamePreferencesState by mutableStateOf(defaultGamePreferences)

    init {
        viewModelScope.launch {
            fetchPreferences()
            fetchAllQuestions()
            resetQuiz()
        }
    }

    fun onQuizEvent(event: QuizScreenEvent) {

        when (event) {
            is QuizScreenEvent.AnswerPressed -> {
                processAnswer(event.answerIndex)
            }
            is QuizScreenEvent.NextQuestionPressed -> {
                setupQuestion(1)
            }
        }
    }

    private fun processAnswer(answer: Int) {
        val answerIsCorrect = (answer == screenState.correctAnswerIndex)
        updateGameStateStats(answerIsCorrect)

        val buttonLabel = if (gameState.isDone()) "Done" else "Next Question"
        val updatedCheckboxes = gradeCheckboxes(answer)
        screenState = screenState.copy(
            continueLabel = buttonLabel,
            continueEnabled = true,
            numCorrect = gameState.numCorrect,
            numberOfQuestions = gameState.quiz.size,
            answerBoxes = updatedCheckboxes,
            answersEnabled = false,
        )
    }

    private fun updateGameStateStats(answerIsCorrect: Boolean) {
        gameState = gameState.copy(
            numCorrect = gameState.numCorrect + if (answerIsCorrect) 1 else 0,
            numWrong = gameState.numWrong + if (answerIsCorrect) 0 else 1,
        )
    }

    private fun gradeCheckboxes(answer: Int): List<TriBoxState> {
        val correctAnswer = screenState.correctAnswerIndex

        val gradedAnswerBoxes = ArrayList<TriBoxState>()
        for (i in 0 until screenState.answerBoxes.size) {
            gradedAnswerBoxes.add(TriBoxState.UNCHECKED)
        }
        gradedAnswerBoxes[answer] = TriBoxState.WRONG
        gradedAnswerBoxes[correctAnswer] = TriBoxState.CORRECT
        return gradedAnswerBoxes
    }

    private fun resetQuiz() {
        gameState = gameState.copy(
            numCorrect = 0,
            numWrong = 0,
            currentQuestionNumber = 0,
        )
        setupQuestion(0)
    }

    private fun setupQuestion(questionNum: Int) {
        gameState = gameState.copy(
            currentQuestionNumber = questionNum,
        )
        prepareScreenForCurrentQuestion()
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

                        gameState = gameState.copy(
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


    private fun prepareScreenForCurrentQuestion() {
        val questionNum = gameState.currentQuestionNumber
        val question = gameState.quiz[questionNum]
        val incorrectAnswers = question.answers
        val numAnswers = incorrectAnswers.size + 1
        val correctAnswerIndex = Random.nextInt(numAnswers)
        val answers = question.answers.shuffled().toMutableList()
        answers.add(correctAnswerIndex, question.correctAnswer)
        val blankAnswerState = List(numAnswers) { TriBoxState.UNCHECKED }

        screenState = screenState.copy(
            category = question.category,
            questionNumber = questionNum,
            question = question.question,
            numberOfQuestions = gameState.numQuestions(),
            correctAnswerIndex = correctAnswerIndex,
            numCorrect = gameState.numCorrect,
            difficulty = question.difficulty.toDisplayString(),
            answers = answers,
            answerBoxes = blankAnswerState,
            answersEnabled = true,
            continueEnabled = false,

        )

        Log.d("QuizViewModel",
            "prepareScreenForCurrentQuestion: ${gameState.numQuestions()}, ${gameState.quiz.size}")
    }
}
