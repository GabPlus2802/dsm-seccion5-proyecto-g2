package com.app.wawacred_grupo2.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
fun IronHistoryScreen(onBack: () -> Unit, ironDoseHistory: List<IronDose>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Historial de Hierro") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        bottomBar = {
            val currentScreen by remember { mutableStateOf(BottomNavItem.Charts) }
            HomeBottomNavigationBar(currentScreen = currentScreen, onScreenSelected = {})
        },
        containerColor = Color(0xFFF0F9FA)
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(ironDoseHistory) { item ->
                IronHistoryCard(item)
            }
        }
    }
}

@Composable
fun IronHistoryCard(item: IronDose) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF2E7D32)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Check, contentDescription = "Completed", tint = Color.White, modifier = Modifier.size(16.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(item.doseTitle, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(Modifier.weight(1f)) {
                        HistoryDetailItem(icon = Icons.Default.Medication, text = item.supplementType)
                        HistoryDetailItem(icon = Icons.Default.CalendarToday, text = item.date)
                    }
                    Column(Modifier.weight(1f)) {
                        HistoryDetailItem(icon = Icons.Default.Medication, text = item.amount) // Re-using icon
                        HistoryDetailItem(icon = Icons.Default.Schedule, text = item.time)
                    }
                }
                HistoryDetailItem(icon = Icons.Default.Notes, text = item.notes)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IronHistoryScreenPreview() {
    Wawacredgrupo2Theme {
        IronHistoryScreen(onBack = {}, ironDoseHistory = emptyList())
    }
}
