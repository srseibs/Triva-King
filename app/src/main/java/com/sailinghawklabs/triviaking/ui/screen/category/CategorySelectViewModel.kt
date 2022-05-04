package com.sailinghawklabs.triviaking.ui.screen.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sailinghawklabs.triviaking.data.remote.OpenTriviaDatabaseApi
import com.sailinghawklabs.triviaking.data.remote.dto.toCategoryList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategorySelectViewModel @Inject constructor(

    // need to use repository
    private val api: OpenTriviaDatabaseApi,
) : ViewModel(){

    private val _categoryState = MutableStateFlow<CategorySelectState>(CategorySelectState.StartState)
    val categoryState = _categoryState.asStateFlow()

    init {
        fetchCategories()
    }


    private fun fetchCategories() {
        viewModelScope.launch{
           try {
               _categoryState.tryEmit(CategorySelectState.LoadingState)
               val categoryResponse = api.getCategories()
               val categoryList = categoryResponse.triviaCategories.map{ it }.sortedBy { it.name }
               delay(2000)
               _categoryState.tryEmit(CategorySelectState.ListAvailable(categoryList.toCategoryList()))

           } catch (e: Exception) {
               _categoryState.emit(
                   CategorySelectState.Error(e.localizedMessage?: "Unknown")
               )
           }
        }
    }

}