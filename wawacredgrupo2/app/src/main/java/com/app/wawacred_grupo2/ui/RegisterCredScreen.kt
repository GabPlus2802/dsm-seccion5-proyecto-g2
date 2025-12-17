package com.app.wawacred_grupo2.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Height
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.wawacred_grupo2.data.db.CredControl
import com.app.wawacred_grupo2.ui.theme.Wawacredgrupo2Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterCredScreen(onBack: () -> Unit, onSave: (CredControl) -> Unit) {
    val credInfoState = rememberCredInfoState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registrar CRED") },
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
                            .background(Color(0xFFFBC02D).copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Height,
                            contentDescription = "CRED Icon",
                            tint = Color(0xFFFBC02D),
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
                CredInfoCard(state = credInfoState)
                Spacer(modifier = Modifier.height(16.dp))
                ReminderSection()
                Spacer(modifier = Modifier.height(24.dp))
                ActionButtons(onCancel = onBack, onSave = {
                    val credControl = CredControl(
                        title = "Control CRED", // You can customize this
                        date = credInfoState.date,
                        time = credInfoState.time,
                        weight = credInfoState.weight,
                        height = credInfoState.height,
                        headCircumference = credInfoState.headCircumference,
                        controlType = credInfoState.controlType,
                        observations = credInfoState.observations
                    )
                    onSave(credControl)
                })
            }
        }
    }
}

class CredInfoState {
    var date by mutableStateOf("")
    var time by mutableStateOf("")
    var weight by mutableStateOf("")
    var height by mutableStateOf("")
    var headCircumference by mutableStateOf("")
    var controlType by mutableStateOf("CRED mensual")
    var observations by mutableStateOf("")
}

@Composable
fun rememberCredInfoState() = remember { CredInfoState() }

@Composable
fun CredInfoCard(state: CredInfoState) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Datos del control", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text("Completa la información del control CRED", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                InfoTextField(label = "Fecha", placeholder = "Hoy", value = state.date, onValueChange = { state.date = it }, modifier = Modifier.weight(1f))
                InfoTextField(label = "Hora", placeholder = "10:00 a. m.", value = state.time, onValueChange = { state.time = it }, modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                InfoTextField(label = "Peso", placeholder = "10.2 kg", value = state.weight, onValueChange = { state.weight = it }, modifier = Modifier.weight(1f))
                InfoTextField(label = "Talla", placeholder = "82 cm", value = state.height, onValueChange = { state.height = it }, modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                InfoTextField(label = "Perímetro cefálico", placeholder = "Opcional", value = state.headCircumference, onValueChange = { state.headCircumference = it }, modifier = Modifier.weight(1f))
                ReminderDropdown(label = "Tipo de control", options = listOf("CRED mensual"), selectedOption = state.controlType, onOptionSelected = { state.controlType = it }, modifier = Modifier.weight(1f)) 
            }
            Spacer(modifier = Modifier.height(8.dp))
            InfoTextField(label = "Observaciones", placeholder = "Añadir nota opcional", value = state.observations, onValueChange = { state.observations = it })
        }
    }
}

// Overloaded InfoTextField and ReminderDropdown to handle state
@Composable
fun InfoTextField(label: String, placeholder: String, value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(label, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, fontSize = 12.sp) },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f),
                focusedBorderColor = Color(0xFF00A98F),
                containerColor = Color.White
            ),
            singleLine = true
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderDropdown(label: String, options: List<String>, selectedOption: String, onOptionSelected: (String) -> Unit, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Text(label, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            OutlinedTextField(
                value = selectedOption,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth().menuAnchor(),
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                shape = MaterialTheme.shapes.medium,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f),
                    focusedBorderColor = Color(0xFF00A98F),
                    containerColor = Color.White
                ),
                textStyle = LocalTextStyle.current.copy(fontSize = 14.sp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 10.dp)
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                options.forEach { option ->
                    DropdownMenuItem(text = { Text(option) }, onClick = { onOptionSelected(option); expanded = false })
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RegisterCredScreenPreview() {
    Wawacredgrupo2Theme {
        RegisterCredScreen(onBack = {}, onSave = {})
    }
}
