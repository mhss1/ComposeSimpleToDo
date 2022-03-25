package com.example.composetodo.feature_task.presentaion.add_task.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    color: Int,
    onSelected: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .border(if (selected) 2.dp else 0.dp, Color.Black, CircleShape)
            .background(Color(color))
            .clickable { onSelected() }
    )
}