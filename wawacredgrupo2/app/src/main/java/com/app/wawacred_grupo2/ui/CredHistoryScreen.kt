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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.wawacred_grupo2.data.db.CredControl
import com.app.wawacred_grupo2.ui.theme.Wawacredgrupo2Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CredHistoryScreen(onBack: () -> Unit, credHistory: List<CredControl>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Historial de CRED") },
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
            items(credHistory) { item ->
                CredHistoryCard(item)
            }
        }
    }
}

@Composable
fun CredHistoryCard(item: CredControl) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF2E7D32)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Check, contentDescription = "Completed", tint = Color.White, modifier = Modifier.size(16.dp))
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(item.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                HistoryDetailItem(icon = Icons.Default.CalendarToday, text = item.date)
                HistoryDetailItem(icon = Icons.Default.Schedule, text = item.time)
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                HistoryDetailItem(icon = Icons.Default.MonitorWeight, text = item.weight)
                HistoryDetailItem(icon = Icons.Default.Height, text = item.height)
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                HistoryDetailItem(icon = Icons.Default.Circle, text = item.headCircumference)
                HistoryDetailItem(icon = Icons.Default.Stethoscope, text = item.controlType)
            }
            if (item.observations.isNotEmpty()) {
                HistoryDetailItem(icon = Icons.Default.Notes, text = item.observations)
            }
        }
    }
}

@Composable
fun HistoryDetailItem(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 4.dp)) {
        Icon(icon, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, color = Color.Gray, fontSize = 14.sp)
    }
}


@Preview(showBackground = true)
@Composable
fun CredHistoryScreenPreview() {
    Wawacredgrupo2Theme {
        CredHistoryScreen(onBack = {}, credHistory = emptyList())
    }
}
