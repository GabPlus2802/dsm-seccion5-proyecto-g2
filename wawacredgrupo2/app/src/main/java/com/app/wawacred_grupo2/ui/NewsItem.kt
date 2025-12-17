package com.app.wawacred_grupo2.ui

import androidx.compose.ui.graphics.vector.ImageVector

data class NewsItem(
    val id: Int,
    val title: String,
    val date: String,
    val source: String,
    val imageUrl: String, // Using URL for remote images, or @DrawableRes for local
    val content: String,
    val icon: ImageVector? = null
)
