package com.example.composetodo.feature_task.presentation.tasks.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetodo.R

@Composable
fun RoundedCornerCheckBox(
    modifier: Modifier = Modifier,
    checked: Boolean = false,
    onCheck: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .border(2.dp, Color.Gray, RoundedCornerShape(14.dp))
            .clickable {
                onCheck()
            }
    ) {
        AnimatedVisibility(visible = checked) {
            Icon(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = "check",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
fun RoundedCornerCheckBoxPreview() {
    val checked = remember { mutableStateOf(true) }
    RoundedCornerCheckBox(
        Modifier.size(70.dp),
        checked = checked.value,
        onCheck = { checked.value = !checked.value },
    )
}