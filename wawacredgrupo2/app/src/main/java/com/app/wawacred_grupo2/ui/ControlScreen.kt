package com.app.wawacred_grupo2.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
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
fun ControlScreen(onRegisterVaccineClick: () -> Unit, onRegisterDoseClick: () -> Unit, onRegisterCredClick: () -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ControlCard(
                title = "Vacunas",
                subtitle = "Registrar vacunas aplicadas",
                buttonText = "Registrar vacuna",
                icon = Icons.Default.Vaccines,
                iconBackgroundColor = Color(0xFF00A98F),
                onClick = onRegisterVaccineClick
            )
        }
        item {
            ControlCard(
                title = "Dosis de hierro",
                subtitle = "Registrar dosis de hierro",
                buttonText = "Registrar dosis",
                icon = Icons.Default.Medication,
                iconBackgroundColor = Color(0xFF00A98F),
                onClick = onRegisterDoseClick
            )
        }
        item {
            ControlCard(
                title = "Crecimiento (CRED)",
                subtitle = "Registrar peso y talla",
                buttonText = "Registrar CRED",
                icon = Icons.Default.Height,
                iconBackgroundColor = Color(0xFFFBC02D),
                onClick = onRegisterCredClick
            )
        }
    }
}

@Composable
fun ControlCard(
    title: String,
    subtitle: String,
    buttonText: String,
    icon: ImageVector,
    iconBackgroundColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(iconBackgroundColor.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconBackgroundColor,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text(subtitle, color = Color.Gray, fontSize = 14.sp)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onClick,
                modifier = Modifier.align(Alignment.End),
                colors = ButtonDefaults.buttonColors(containerColor = if (title.contains("CRED")) Color(0xFF00A98F) else Color(0xFF00A98F)), // Adjusted color for CRED
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(buttonText)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFEEFAFC)
@Composable
fun ControlScreenPreview() {
    Wawacredgrupo2Theme {
        ControlScreen(onRegisterVaccineClick = {}, onRegisterDoseClick = {}, onRegisterCredClick = {})
    }
}
