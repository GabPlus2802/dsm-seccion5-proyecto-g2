package com.app.wawacred_grupo2.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.wawacred_grupo2.R
import com.app.wawacred_grupo2.ui.theme.Wawacredgrupo2Theme
import kotlinx.coroutines.launch

data class OnboardingItem(
    val title: String,
    val description: String,
    val icon: ImageVector? = null,
    val isWelcomeScreen: Boolean = false
)

private val onboardingItems = listOf(
    OnboardingItem(
        isWelcomeScreen = true,
        title = "¡Bienvenido a WawaCRED!",
        description = "El cuidado de tu wawa en tus manos",
    ),
    OnboardingItem(
        title = "Controla el crecimiento de tu wawa",
        description = "Lleva un registro claro de peso y talla en cada control de tu wawa.",
        icon = Icons.Default.ChildCare
    ),
    OnboardingItem(
        title = "Adaptación a tu lengua",
        description = "Ajuste de contenido en tu lengua originaria",
        icon = Icons.Default.Translate
    ),
    OnboardingItem(
        title = "Puede funcionar sin conexión a internet",
        description = "Puedes guardar datos de tus controles sin conexión a internet.",
        icon = Icons.Default.WifiOff
    )
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(onFinish: () -> Unit) {
    val pagerState = rememberPagerState(pageCount = { onboardingItems.size })

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFEEFAFC)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) { page ->
                OnboardingPage(item = onboardingItems[page], pagerState = pagerState)
            }
            OnboardingNavigation(pagerState = pagerState, onFinish = onFinish)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingPage(item: OnboardingItem, pagerState: PagerState) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(vertical = 32.dp, horizontal = 24.dp)
                .fillMaxWidth()
        ) {
            if (item.isWelcomeScreen) {
                WelcomePageHeader()
            } else {
                item.icon?.let { OnboardingIcon(icon = it) }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = item.title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = item.description,
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            PagerIndicator(pageCount = onboardingItems.size, currentPage = pagerState.currentPage)
        }
    }
}

@Composable
private fun WelcomePageHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.mipmap.ic_launcher),
            contentDescription = "Logo",
            modifier = Modifier.size(60.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color(0xFFF06C4B))) {
                    append("Wawa")
                }
                withStyle(style = SpanStyle(color = Color(0xFF005A79))) {
                    append("CRED")
                }
            },
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun OnboardingIcon(icon: ImageVector) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(Color(0xFFE0F7FA)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(36.dp),
            tint = Color(0xFF00A98F)
        )
    }
}


@Composable
fun PagerIndicator(pageCount: Int, currentPage: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { iteration ->
            val color = if (currentPage == iteration) Color(0xFF00A98F) else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(10.dp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingNavigation(pagerState: PagerState, onFinish: () -> Unit) {
    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 24.dp),
        horizontalArrangement = if (pagerState.currentPage > 0) Arrangement.SpaceBetween else Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (pagerState.currentPage > 0) {
            Button(
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                },
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black)
            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Atrás")
            }
        }

        val isLastPage = pagerState.currentPage == onboardingItems.size - 1
        Button(
            onClick = {
                if (!isLastPage) {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                } else {
                    onFinish()
                }
            },
            modifier = Modifier.height(56.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00A98F))
        ) {
            Text(text = if (isLastPage) "Comenzar" else "Siguiente", color = Color.White, fontSize = 18.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = if (isLastPage) Icons.Default.Check else Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    Wawacredgrupo2Theme {
        OnboardingScreen(onFinish = {})
    }
}
