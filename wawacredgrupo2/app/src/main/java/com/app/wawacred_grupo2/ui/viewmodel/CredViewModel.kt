package com.app.wawacred_grupo2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.app.wawacred_grupo2.data.db.CredControl
import com.app.wawacred_grupo2.data.db.CredControlDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CredViewModel(private val credControlDao: CredControlDao) : ViewModel() {

    val allCredControls: StateFlow<List<CredControl>> = credControlDao.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    fun insertCredControl(credControl: CredControl) {
        viewModelScope.launch {
            credControlDao.insert(credControl)
        }
    }
}

class CredViewModelFactory(private val credControlDao: CredControlDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CredViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CredViewModel(credControlDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
