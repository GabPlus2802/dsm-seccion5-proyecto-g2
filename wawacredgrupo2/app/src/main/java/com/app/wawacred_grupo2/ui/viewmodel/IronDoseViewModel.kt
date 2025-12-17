package com.app.wawacred_grupo2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.app.wawacred_grupo2.data.db.IronDose
import com.app.wawacred_grupo2.data.db.IronDoseDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class IronDoseViewModel(private val ironDoseDao: IronDoseDao) : ViewModel() {

    val allIronDoses: StateFlow<List<IronDose>> = ironDoseDao.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    fun insertIronDose(ironDose: IronDose) {
        viewModelScope.launch {
            ironDoseDao.insert(ironDose)
        }
    }
}

class IronDoseViewModelFactory(private val ironDoseDao: IronDoseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IronDoseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return IronDoseViewModel(ironDoseDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
