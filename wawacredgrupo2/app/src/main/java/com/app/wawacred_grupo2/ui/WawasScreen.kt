package com.app.wawacred_grupo2.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.wawacred_grupo2.R
import com.app.wawacred_grupo2.ui.theme.Wawacredgrupo2Theme

data class WawaItem(
    val name: String,
    val document: String,
    val birthDate: String,
    val weight: String,
    val gender: String,
    val bloodType: String,
    val location: String,
    val height: String,
    val imageRes: Int
)

val wawaItems = listOf(
    WawaItem(
        name = "Azucena Quispe Flores",
        document = "75924124",
        birthDate = "12/08/2022",
        weight = "11.3 kg",
        gender = "Femenino",
        bloodType = "O+",
        location = "Lima",
        height = "86 cm",
        imageRes = R.drawable.ic_launcher_background
    ),
    WawaItem(
        name = "Miguel Quispe Flores",
        document = "74032151",
        birthDate = "16/05/2024",
        weight = "9 kg",
        gender = "Masculino",
        bloodType = "A+",
        location = "Lima",
        height = "78 cm",
        imageRes = R.drawable.ic_launcher_background
    )
)

@Composable
fun WawasScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(wawaItems) { item ->
            WawaCard(item = item)
        }
    }
}

@Composable
fun WawaCard(item: WawaItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(item.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Column(Modifier.weight(1f)) {
                        WawaInfoDetail(icon = Icons.Default.CreditCard, text = item.document)
                        WawaInfoDetail(icon = Icons.Default.CalendarToday, text = item.birthDate)
                        WawaInfoDetail(icon = Icons.Default.MonitorWeight, text = item.weight)
                        WawaInfoDetail(icon = Icons.Default.Wc, text = item.gender)
                    }
                    Column(Modifier.weight(1f)) {
                        WawaInfoDetail(icon = Icons.Default.Bloodtype, text = item.bloodType)
                        WawaInfoDetail(icon = Icons.Default.LocationOn, text = item.location)
                        WawaInfoDetail(icon = Icons.Default.Height, text = item.height)
                    }
                }
            }
        }
    }
}

@Composable
fun WawaInfoDetail(icon: ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 4.dp)
    ) {
        Icon(icon, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, fontSize = 14.sp, color = Color.Gray)
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFEEFAFC)
@Composable
fun WawasScreenPreview() {
    Wawacredgrupo2Theme {
        WawasScreen()
    }
}
