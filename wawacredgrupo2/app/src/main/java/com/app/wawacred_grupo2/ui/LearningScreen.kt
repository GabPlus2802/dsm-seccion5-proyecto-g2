package com.app.wawacred_grupo2.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.wawacred_grupo2.ui.theme.Wawacredgrupo2Theme

data class LearningArticle(
    val title: String,
    val subtitle: String,
    val description: String,
    val icon: ImageVector,
    val category: String,
    val videoId: String
)

val learningArticles = listOf(
    // Vacunas
    LearningArticle("Vacunas importantes", "Por qué cada vacuna es clave", "Descripción detallada sobre la importancia de las vacunas.", Icons.Default.Vaccines, "Vacunas", "VIDEO_ID_1"),
    LearningArticle("Calendario de vacunación", "Cuándo le toca cada vacuna a tu wawa", "Descripción detallada sobre el calendario de vacunación.", Icons.Default.CalendarToday, "Vacunas", "VIDEO_ID_2"),
    LearningArticle("Después de la vacuna", "Qué es normal y cuándo ir al centro de salud", "Descripción detallada sobre los cuidados post-vacunación.", Icons.Default.Schedule, "Vacunas", "VIDEO_ID_3"),
    LearningArticle("Vacunas en los primeros meses", "Protección desde que tu wawa es pequeña", "Descripción detallada sobre las vacunas de los primeros meses.", Icons.Default.ChildCare, "Vacunas", "VIDEO_ID_4"),
    
    // Hierro
    LearningArticle("Cómo prevenir la anemia", "Alimentos y hábitos que ayudan a tu wawa", "Descubre qué alimentos ricos en hierro ayudan a prevenir la anemia y cómo incorporarlos en la dieta diaria de tu pequeño para asegurar un crecimiento fuerte y saludable.", Icons.Default.Shield, "Hierro", "YOUTUBE_VIDEO_ID"),
    LearningArticle("Platos ricos en hierro", "Ideas sencillas con ingredientes locales", "Descripción detallada sobre platos ricos en hierro.", Icons.Default.FoodBank, "Hierro", "VIDEO_ID_5"),
    LearningArticle("Crecimiento mes a mes", "Qué esperar del desarrollo de tu wawa", "Descripción detallada sobre el crecimiento mes a mes.", Icons.Default.TrendingUp, "Hierro", "VIDEO_ID_6"),
    LearningArticle("Vacunas importantes", "Por qué cada vacuna es clave", "Descripción detallada sobre la importancia de las vacunas.", Icons.Default.Vaccines, "Hierro", "VIDEO_ID_7"),
    LearningArticle("Cuidados diarios", "Rutinas simples para mantenerlo sano", "Descripción detallada sobre cuidados diarios.", Icons.Default.FavoriteBorder, "Hierro", "VIDEO_ID_8"),

    // CRED
    LearningArticle("Crecimiento mes a mes", "Qué cambios esperar en cada etapa de tu wawa", "Descripción detallada sobre el crecimiento mes a mes.", Icons.Default.Schedule, "CRED", "VIDEO_ID_9"),
    LearningArticle("Cómo leer la curva de crecimiento", "Entiende las gráficas de peso y talla CRED", "Descripción detallada sobre cómo leer la curva de crecimiento.", Icons.Default.Height, "CRED", "VIDEO_ID_10"),
    LearningArticle("Peso y talla saludables", "Señales de que tu wawa va creciendo bien", "Descripción detallada sobre peso y talla saludables.", Icons.Default.BarChart, "CRED", "VIDEO_ID_11"),
    LearningArticle("Cuándo ir al control CRED", "Fechas clave para no perder ningún control", "Descripción detallada sobre cuándo ir al control CRED.", Icons.Default.Event, "CRED", "VIDEO_ID_12"),
    LearningArticle("Estimulación temprana sencilla", "Juegos diarios para apoyar su desarrollo", "Descripción detallada sobre estimulación temprana.", Icons.Default.Extension, "CRED", "VIDEO_ID_13"),

    // Cuidado
    LearningArticle("Higiene diaria sencilla", "Cómo bañar y limpiar a tu wawa con seguridad", "Descripción detallada sobre higiene diaria.", Icons.Default.Bathtub, "Cuidado", "VIDEO_ID_14"),
    LearningArticle("Sueño y rutinas tranquilas", "Pasos para ayudarle a dormir mejor cada noche", "Descripción detallada sobre sueño y rutinas.", Icons.Default.NightsStay, "Cuidado", "VIDEO_ID_15"),
    LearningArticle("Cariño y juego seguro", "Ideas de juego que fortalecen el vínculo", "Descripción detallada sobre cariño y juego seguro.", Icons.Default.Favorite, "Cuidado", "VIDEO_ID_16"),
    LearningArticle("Casa segura para tu wawa", "Pequeños cambios para evitar caídas y golpes", "Descripción detallada sobre casa segura.", Icons.Default.House, "Cuidado", "VIDEO_ID_17"),
    LearningArticle("Cuándo preocuparse y acudir al centro", "Señales sencillas para saber si necesita atención", "Descripción detallada sobre cuándo preocuparse.", Icons.Default.Warning, "Cuidado", "VIDEO_ID_18")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearningScreen(onArticleClick: (LearningArticle) -> Unit) {
    val categories = listOf("Vacunas", "Hierro", "CRED", "Cuidado")
    var selectedCategory by remember { mutableStateOf(categories[0]) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                categories.forEach { category ->
                    FilterChip(
                        selected = category == selectedCategory,
                        onClick = { selectedCategory = category },
                        label = { Text(category) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Color(0xFF00A98F),
                            selectedLabelColor = Color.White
                        )
                    )
                }
            }
        }
        items(learningArticles.filter { it.category == selectedCategory }) { article ->
            ArticleCard(article, onClick = { onArticleClick(article) })
        }
    }
}

@Composable
fun ArticleCard(article: LearningArticle, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = article.icon,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(article.title, fontWeight = FontWeight.Bold)
                Text(article.subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Ver artículo",
                tint = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFEEFAFC)
@Composable
fun LearningScreenPreview() {
    Wawacredgrupo2Theme {
        LearningScreen(onArticleClick = {})
    }
}
