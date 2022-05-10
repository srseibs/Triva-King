package com.sailinghawklabs.triviaking.ui.screen.quiz

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sailinghawklabs.triviaking.domain.model.Question
import com.sailinghawklabs.triviaking.domain.model.defaultGamePreferences
import com.sailinghawklabs.triviaking.domain.repository.QuizRepository
import com.sailinghawklabs.triviaking.ui.theme.util.TriBoxState
import com.sailinghawklabs.triviaking.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class QuizViewModel @Inject constructor(
    // need to use repository
    private val repository: QuizRepository,

//    private val getQuestionSetUseCase: GetQuestionSet,

) : ViewModel() {

    private val _viewState = MutableStateFlow(QuizOverallState())
    val viewState = _viewState.asStateFlow()

    private val gamePreferencesState = mutableStateOf(defaultGamePreferences)

    init {
        fetchAllQuestions()
    }


    private fun fetchAllQuestions() {
        viewModelScope.launch {

            // TODO: domain should use enums for difficulty, and the string processing should
            // happen in the repository.

            try {
                viewModelScope.launch {
                    repository.fetchQuestionSet(
                        numberOfQuestions = gamePreferencesState.value.numberOfQuestions,
                        categoryId = gamePreferencesState.value.category?.id,
                        difficultyString = gamePreferencesState.value.difficulty.toString()
                            .lowercase()
                    ).collect() { result ->
                        when (result) {
                            is Result.Success -> {


                                val newQuestion = result.data?.get(0)  // for now
                                _viewState.value = _viewState.value.copy(
                                    screenState = prepareQuestion(question = newQuestion!!)
                                )
                            }

                        }
                    }
                }
            } catch (e: Exception) {

            }

        }

    }


    private fun prepareQuestion(question: Question): QuizScreenState {
        val incorrectAnswers = question.answers
        val numAnswers = incorrectAnswers.size + 1
        val correctAnswerIndex = Random.nextInt(numAnswers)
        val answers = question.answers.shuffled().toMutableList()
        answers.add(correctAnswerIndex, question.correctAnswer)
        val answerState = List(numAnswers) { TriBoxState.UNCHECKED }

        return _viewState.value.screenState.copy(
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