package com.app.wawacred_grupo2.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.wawacred_grupo2.ui.theme.Wawacredgrupo2Theme

@Composable
fun StatusScreen(onCredHistoryClick: () -> Unit, onVaccineHistoryClick: () -> Unit, onIronHistoryClick: () -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item {
            Text("Revisa cómo va tu wawa", style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))
            WawaSelector()
        }
        item { GrowthStatusCard(onHistoryClick = onCredHistoryClick) }
        item { VaccineStatusCard(onHistoryClick = onVaccineHistoryClick) }
        item { IronStatusCard(onHistoryClick = onIronHistoryClick) }
    }
}

@Composable
fun GrowthStatusCard(onHistoryClick: () -> Unit) {
    StatusCard(
        title = "Crecimiento",
        subtitle = "Último control: 05/09/2024",
        statusText = "Correcto",
        statusColor = Color(0xFF2E7D32),
        icon = Icons.Default.TrendingUp,
        onHistoryClick = onHistoryClick
    ) {
        StatusDetailRow(label = "Peso", value = "10.2 kg")
        StatusDetailRow(label = "Talla", value = "82 cm")
        StatusDetailRow(label = "Perímetro cefálico", value = "46 cm")
    }
}

@Composable
fun VaccineStatusCard(onHistoryClick: () -> Unit) {
    StatusCard(
        title = "Vacunas",
        subtitle = "Próxima: 20/09/2024",
        statusText = "Revisar",
        statusColor = Color(0xFFFBC02D),
        icon = Icons.Default.Vaccines,
        onHistoryClick = onHistoryClick
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Check, contentDescription = "Aplicadas", tint = Color.Gray)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Aplicadas", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            Text("8/10 (80%)", color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = { 0.8f },
            modifier = Modifier.fillMaxWidth().height(8.dp).clip(CircleShape),
            color = Color(0xFF00A98F)
        )
    }
}

@Composable
fun IronStatusCard(onHistoryClick: () -> Unit) {
    StatusCard(
        title = "Hierro",
        subtitle = "",
        statusText = "Bien",
        statusColor = Color(0xFF2E7D32),
        icon = Icons.Default.Medication,
        onHistoryClick = onHistoryClick
    ) {
        StatusDetailRow(label = "Última dosis", value = "Ayer")
        StatusDetailRow(label = "Dosis esta semana", value = "5/7")
        StatusDetailRow(label = "Cumplimiento", value = "70%")
        Spacer(modifier = Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = { 0.7f },
            modifier = Modifier.fillMaxWidth().height(8.dp).clip(CircleShape),
            color = Color(0xFF00A98F)
        )
    }
}

@Composable
fun StatusCard(
    title: String,
    subtitle: String,
    statusText: String,
    statusColor: Color,
    icon: ImageVector,
    onHistoryClick: () -> Unit, 
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    if (subtitle.isNotEmpty()) {
                        Text(subtitle, color = Color.Gray, fontSize = 14.sp)
                    }
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(statusColor.copy(alpha = 0.1f))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(statusText, color = statusColor, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(content = content)
            TextButton(onClick = onHistoryClick, modifier = Modifier.align(Alignment.End)) {
                Icon(Icons.Default.ReceiptLong, contentDescription = "Ver historial", modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Ver historial", fontSize = 12.sp)
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Composable
fun StatusDetailRow(label: String, value: String) {
    Row(modifier = Modifier.padding(vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(label, color = Color.Gray, modifier = Modifier.weight(1f))
        Text(value, fontWeight = FontWeight.SemiBold)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFEEFAFC)
@Composable
fun StatusScreenPreview() {
    Wawacredgrupo2Theme {
        StatusScreen(onCredHistoryClick = {}, onVaccineHistoryClick = {}, onIronHistoryClick = {})
    }
}
