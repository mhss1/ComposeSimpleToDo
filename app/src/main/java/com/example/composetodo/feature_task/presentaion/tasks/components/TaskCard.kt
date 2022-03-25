package com.example.composetodo.feature_task.presentation.tasks.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetodo.feature_task.domain.model.Task
import com.example.composetodo.util.formatted

@Composable
fun TaskCard(
    modifier: Modifier = Modifier,
    task: Task,
    checked: Boolean = false,
    onCheck: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = 12.dp
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color(task.color))
                .padding(horizontal = 12.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RoundedCornerCheckBox(
                checked = checked,
                onCheck = { onCheck() },
                modifier = Modifier.size(45.dp)
            )
            Text(
                text = task.title,
                style = MaterialTheme.typography.h6,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = "Due Date\n${task.dueDate.formatted()}",
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun TaskCardPreview() {
    TaskCard(
        task = Task(
            "This is a Test task title",
            1652306400000,
            Color.Cyan.toArgb()
        ),
        checked = true
    ) {}
}