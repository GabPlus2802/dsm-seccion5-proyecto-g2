package com.app.wawacred_grupo2.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.wawacred_grupo2.data.db.Vaccine
import com.app.wawacred_grupo2.ui.theme.Wawacredgrupo2Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterVaccineScreen(onBack: () -> Unit, onSave: (Vaccine) -> Unit) {
    val vaccineInfoState = rememberVaccineInfoState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registrar vacuna") },
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
                            imageVector = Icons.Default.Vaccines,
                            contentDescription = "Vaccine Icon",
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
                VaccineInfoCard(state = vaccineInfoState)
                Spacer(modifier = Modifier.height(16.dp))
                ReminderSection()
                Spacer(modifier = Modifier.height(24.dp))
                ActionButtons(onCancel = onBack, onSave = {
                    val vaccine = Vaccine(
                        name = "", // You can add a field for the vaccine name
                        dose = vaccineInfoState.dose,
                        protectsAgainst = vaccineInfoState.protectsAgainst,
                        date = vaccineInfoState.date,
                        time = vaccineInfoState.time
                    )
                    onSave(vaccine)
                })
            }
        }
    }
}

class VaccineInfoState {
    var dose by mutableStateOf("")
    var protectsAgainst by mutableStateOf("")
    var date by mutableStateOf("")
    var time by mutableStateOf("")
}

@Composable
fun rememberVaccineInfoState() = remember { VaccineInfoState() }

@Composable
fun VaccineInfoCard(state: VaccineInfoState) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Info, contentDescription = "Info", tint = Color.Gray)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Información de la vacuna", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                InfoTextField(label = "Dosis", placeholder = "Ej. 1ra dosis", value = state.dose, onValueChange = { state.dose = it }, modifier = Modifier.weight(1f))
                InfoTextField(label = "Protege contra", placeholder = "Ej. Anemia", value = state.protectsAgainst, onValueChange = { state.protectsAgainst = it }, modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                InfoTextField(label = "Fecha", placeholder = "Selecciona fecha", value = state.date, onValueChange = { state.date = it }, modifier = Modifier.weight(1f))
                InfoTextField(label = "Hora", placeholder = "Selecciona hora", value = state.time, onValueChange = { state.time = it }, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterVaccineScreenPreview() {
    Wawacredgrupo2Theme {
        RegisterVaccineScreen(onBack = {}, onSave = {})
    }
}
