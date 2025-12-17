package com.app.wawacred_grupo2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.app.wawacred_grupo2.data.db.Vaccine
import com.app.wawacred_grupo2.data.db.VaccineDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class VaccineViewModel(private val vaccineDao: VaccineDao) : ViewModel() {

    val allVaccines: StateFlow<List<Vaccine>> = vaccineDao.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    fun insertVaccine(vaccine: Vaccine) {
        viewModelScope.launch {
            vaccineDao.insert(vaccine)
        }
    }
}

class VaccineViewModelFactory(private val vaccineDao: VaccineDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VaccineViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VaccineViewModel(vaccineDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
