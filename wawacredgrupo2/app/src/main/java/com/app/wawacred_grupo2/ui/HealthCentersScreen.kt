package com.app.wawacred_grupo2.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
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
import com.app.wawacred_grupo2.ui.theme.Wawacredgrupo2Theme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

data class HealthCenter(
    val name: String,
    val address: String,
    val schedule: String,
    val phone: String,
    val distance: String,
    val position: LatLng
)

val healthCenters = listOf(
    HealthCenter(
        name = "Hospital Nacional Arzobispo Loayza",
        address = "Av. Alfonso Ugarte 848, Lima",
        schedule = "8:00 a.m. - 5:00 p.m.",
        phone = "(01) 5094800",
        distance = "550 m",
        position = LatLng(-12.049, -77.042)
    ),
    HealthCenter(
        name = "Centro de Salud de Breña",
        address = "Jr. Fulano de Tal 123, Breña",
        schedule = "9:00 a.m. - 4:00 p.m.",
        phone = "(01) 1234567",
        distance = "1.2 km",
        position = LatLng(-12.055, -77.05)
    )
)

@Composable
fun HealthCentersScreen() {
    var selectedCenter by remember { mutableStateOf<HealthCenter?>(null) }
    val lima = LatLng(-12.046374, -77.042793)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(lima, 14f)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f)) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                healthCenters.forEach { center ->
                    Marker(
                        state = MarkerState(position = center.position),
                        title = center.name,
                        snippet = center.address,
                        onClick = {
                            selectedCenter = center
                            false
                        }
                    )
                }
            }
        }
        selectedCenter?.let {
            HealthCenterInfoCard(center = it)
        }
    }
}

@Composable
fun HealthCenterInfoCard(center: HealthCenter) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.LocalHospital,
                    contentDescription = "Hospital Icon",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(center.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.weight(1f))
                Icon(Icons.Default.Send, contentDescription = "Distance", modifier = Modifier.size(16.dp), tint = Color.Gray)
                Spacer(modifier = Modifier.width(4.dp))
                Text(center.distance, color = Color.Gray, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            InfoRow(icon = Icons.Default.LocationOn, text = center.address)
            InfoRow(icon = Icons.Default.Schedule, text = center.schedule)
            InfoRow(icon = Icons.Default.Phone, text = center.phone)
        }
    }
}

@Composable
fun InfoRow(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 8.dp)) {
        Icon(icon, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, fontSize = 14.sp)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFEEFAFC)
@Composable
fun HealthCentersScreenPreview() {
    Wawacredgrupo2Theme {
        HealthCentersScreen()
    }
}
