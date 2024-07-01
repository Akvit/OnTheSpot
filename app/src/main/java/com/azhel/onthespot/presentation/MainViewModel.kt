package com.azhel.onthespot.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azhel.onthespot.domain.BootEventModel
import com.azhel.onthespot.domain.BootEventRepositoryRead
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(private val bootEventRepositoryRead: BootEventRepositoryRead): ViewModel() {

    private val _state = MutableStateFlow(listOf<BootEventModel>())
    val state = _state.asStateFlow()

    init {
        update()
    }

    fun update() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = bootEventRepositoryRead.getAllBootEvents()
            _state.update { list }
        }
    }
}
