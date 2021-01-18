package com.shevchenkovtwo.homework.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shevchenkovtwo.homework.data.MoviesRepository
import com.shevchenkovtwo.homework.data.models.Actor
import com.shevchenkovtwo.homework.network.ResponsesLogging
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class MovieDetailsViewModel(private val repository: MoviesRepository) : ViewModel() {
    val stateFlow: MutableStateFlow<ResponsesLogging<List<Actor>>> =
        MutableStateFlow(ResponsesLogging.Loading())

    fun loadActors(id: Int) = viewModelScope.launch {
        stateFlow.value = repository.getActorsWithId(id)
    }
}