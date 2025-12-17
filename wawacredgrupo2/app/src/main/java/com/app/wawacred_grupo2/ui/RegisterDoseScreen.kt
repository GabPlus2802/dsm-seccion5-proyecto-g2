package com.app.wawacred_grupo2.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.wawacred_grupo2.data.db.IronDose
import com.app.wawacred_grupo2.ui.theme.Wawacredgrupo2Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterDoseScreen(onBack: () -> Unit, onSave: (IronDose) -> Unit) {
    val doseInfoState = rememberDoseInfoState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registrar dosis de hierro") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF00A98F).copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Medication,
                            contentDescription = "Dose Icon",
                            tint = Color(0xFF00A98F),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = Color(0xFFF0F9FA)
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item {
                WawaSelector()
                Spacer(modifier = Modifier.height(16.dp))
                DoseInfoCard(state = doseInfoState)
                Spacer(modifier = Modifier.height(16.dp))
                ReminderSection()
                Spacer(modifier = Modifier.height(24.dp))
                ActionButtons(onCancel = onBack, onSave = {
                    val ironDose = IronDose(
                        doseTitle = "", // You can add a field for this
                        supplementType = doseInfoState.supplementType,
                        amount = doseInfoState.amount,
                        date = doseInfoState.date,
                        time = doseInfoState.time,
                        notes = doseInfoState.notes
                    )
                    onSave(ironDose)
                })
            }
        }
    }
}

class DoseInfoState {
    var supplementType by mutableStateOf("Gotas de hierro")
    var amount by mutableStateOf("")
    var date by mutableStateOf("")
    var time by mutableStateOf("")
    var notes by mutableStateOf("")
}

@Composable
fun rememberDoseInfoState() = remember { DoseInfoState() }

@Composable
fun DoseInfoCard(state: DoseInfoState) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Detalles de la dosis", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text("Completa la información de la dosis de hierro", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                 ReminderDropdown(label = "Tipo de suplemento", options = listOf("Gotas de hierro"), selectedOption = state.supplementType, onOptionSelected = { state.supplementType = it }, modifier = Modifier.weight(1f))
                 InfoTextField(label = "Dosis", placeholder = "5 ml", value = state.amount, onValueChange = { state.amount = it }, modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                InfoTextField(label = "Fecha de aplicación", placeholder = "Hoy", value = state.date, onValueChange = { state.date = it }, modifier = Modifier.weight(1f))
                InfoTextField(label = "Hora", placeholder = "08:30 a. m.", value = state.time, onValueChange = { state.time = it }, modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(8.dp))
            InfoTextField(label = "Notas", placeholder = "Añadir nota opcional", value = state.notes, onValueChange = { state.notes = it })
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RegisterDoseScreenPreview() {
    Wawacredgrupo2Theme {
        RegisterDoseScreen(onBack = {}, onSave = {})
    }
}
