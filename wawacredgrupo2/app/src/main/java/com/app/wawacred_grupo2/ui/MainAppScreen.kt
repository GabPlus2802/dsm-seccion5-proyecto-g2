package com.app.wawacred_grupo2.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.wawacred_grupo2.R
import com.app.wawacred_grupo2.ui.theme.Wawacredgrupo2Theme

enum class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    Home("home", Icons.Default.Home, "Home"),
    Wawas("wawas", Icons.Default.History, "Wawas"), // Using History as placeholder icon
    Appointments("appointments", Icons.Default.EditCalendar, "Control"),
    Charts("charts", Icons.Default.BarChart, "Estado"),
    Learning("learning", Icons.Default.Book, "Aprendizaje"),
    HealthCenters("health_centers", Icons.Default.LocalHospital, "Centros")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppScreen(
    onEventClick: (NewsItem) -> Unit, 
    onProfileClick: () -> Unit, 
    onRegisterVaccineClick: () -> Unit,
    onRegisterDoseClick: () -> Unit,
    onRegisterCredClick: () -> Unit,
    onCredHistoryClick: () -> Unit,
    onVaccineHistoryClick: () -> Unit,
    onIronHistoryClick: () -> Unit,
    onArticleClick: (LearningArticle) -> Unit
) {
    var currentScreen by remember { mutableStateOf(BottomNavItem.Home) }

    Scaffold(
        topBar = { 
            val topBarTitle = when(currentScreen) {
                BottomNavItem.Wawas -> "Wawas"
                BottomNavItem.Appointments -> "Control"
                BottomNavItem.Charts -> "Estado"
                BottomNavItem.Learning -> "Aprendizaje"
                BottomNavItem.HealthCenters -> "Centros de salud"
                else -> "WawaCRED"
            }
            HomeTopAppBar(onProfileClick = onProfileClick, title = topBarTitle) 
        },
        bottomBar = { 
            HomeBottomNavigationBar(
                currentScreen = currentScreen,
                onScreenSelected = { screen -> currentScreen = screen }
            ) 
        },
        containerColor = Color(0xFFEEFAFC)
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (currentScreen) {
                BottomNavItem.Home -> HomeScreenContent(onEventClick = onEventClick)
                BottomNavItem.Wawas -> WawasScreen()
                BottomNavItem.Appointments -> ControlScreen(
                    onRegisterVaccineClick = onRegisterVaccineClick,
                    onRegisterDoseClick = onRegisterDoseClick,
                    onRegisterCredClick = onRegisterCredClick
                )
                BottomNavItem.Charts -> StatusScreen(
                    onCredHistoryClick = onCredHistoryClick,
                    onVaccineHistoryClick = onVaccineHistoryClick,
                    onIronHistoryClick = onIronHistoryClick
                )
                BottomNavItem.Learning -> LearningScreen(onArticleClick = onArticleClick)
                BottomNavItem.HealthCenters -> HealthCentersScreen()
                else -> {
                    // Placeholder for other screens
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "${currentScreen.label} Screen")
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreenContent(onEventClick: (NewsItem) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { 
            Spacer(modifier = Modifier.height(8.dp))
            Text("Novedades", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold) 
        }
        items(newsItems) { item ->
            NewsCard(item, onEventClick = { onEventClick(item) })
        }
        item { 
            Spacer(modifier = Modifier.height(8.dp))
            Text("Recordatorios", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        }
        items(reminderItems) { item ->
            ReminderCard(item)
        }
    }
}

@Composable
fun HomeTopAppBar(onProfileClick: () -> Unit, title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (title == "WawaCRED") {
                Image(
                    painter = painterResource(id = R.mipmap.ic_launcher),
                    contentDescription = "Logo",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color(0xFFF06C4B))) { append("Wawa") }
                        withStyle(style = SpanStyle(color = Color(0xFF005A79))) { append("CRED") }
                    },
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            } else {
                Text(title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        }
        IconButton(onClick = onProfileClick) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Profile",
                modifier = Modifier.size(36.dp),
                tint = Color(0xFF00A98F)
            )
        }
    }
}

@Composable
fun HomeBottomNavigationBar(currentScreen: BottomNavItem, onScreenSelected: (BottomNavItem) -> Unit) {
    val items = BottomNavItem.values()
    NavigationBar(
        containerColor = Color.White,
    ) {
        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = screen.label) },
                label = { Text(screen.label) },
                selected = currentScreen == screen,
                onClick = { onScreenSelected(screen) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF00A98F),
                    unselectedIconColor = Color.Gray,
                    indicatorColor = Color(0xFFE0F7FA)
                )
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainAppScreenPreview() {
    Wawacredgrupo2Theme {
        MainAppScreen(
            onEventClick = {}, 
            onProfileClick = {}, 
            onRegisterVaccineClick = {}, 
            onRegisterDoseClick = {},
            onRegisterCredClick = {},
            onCredHistoryClick = {},
            onVaccineHistoryClick = {},
            onIronHistoryClick = {},
            onArticleClick = {}
        )
    }
}
