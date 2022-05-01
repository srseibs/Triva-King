package com.sailinghawklabs.triviaking

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface

import androidx.compose.ui.Modifier

import com.sailinghawklabs.triviaking.data.remote.OpenTriviaDatabaseApi
import com.sailinghawklabs.triviaking.data.remote.dto.ResponseCode
import com.sailinghawklabs.triviaking.ui.theme.TriviaKingTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var api: OpenTriviaDatabaseApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Main).launch {
            val results = api.getCategories()
            Log.d("MainActivity", "onCreate: $results")
            
            val question = api.getQuestions(
                quantity = 1,
                categoryId = null,
            )
            val resultMessage = ResponseCode.toMessage(question.responseCode)
            Log.d("MainActivity", "onCreate: $resultMessage")
            Log.d("MainActivity", "onCreate: $question")


            val question2 = api.getQuestions(
                quantity = 1,
                categoryId = 32,
            )
            val resultMessage2 = ResponseCode.toMessage(question2.responseCode)
            Log.d("MainActivity", "onCreate: $resultMessage2")
            Log.d("MainActivity", "onCreate: $question2")
        }

        setContent {
            TriviaKingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {





                }
            }
        }
    }
}
