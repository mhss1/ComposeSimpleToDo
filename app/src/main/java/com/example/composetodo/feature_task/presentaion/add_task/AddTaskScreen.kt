package com.example.composetodo.feature_task.presentaion.add_task

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composetodo.R
import com.example.composetodo.feature_task.presentaion.add_task.components.ColorItem
import com.example.composetodo.util.formatted
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddTaskScreen(
    navController: NavController,
    viewModel: AddTaskViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as AppCompatActivity
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val colorsList = listOf(
        Color.DarkGray.toArgb(),
        ColorUtils.blendARGB(Color.Cyan.toArgb(), 0x000000, 0.3f),
        ColorUtils.blendARGB(Color.Magenta.toArgb(), 0x000000, 0.3f),
        ColorUtils.blendARGB(Color.Blue.toArgb(), 0x000000, 0.3f),
        ColorUtils.blendARGB(Color.Red.toArgb(), 0x000000, 0.3f),
    )
    val backgroundColor = remember {
        Animatable(
            Color(viewModel.color.value)
        )
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddTaskViewModel.UiEvent.ShowSnackBar ->
                    scaffoldState.snackbarHostState.showSnackbar(
                        event.message
                    )
                is AddTaskViewModel.UiEvent.TaskSaved ->
                    navController.navigateUp()
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(
                    AddTaskEvent.SaveTask
                )
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_save),
                    contentDescription = "save note"
                )
            }
        }
    ) {
        Column(
            Modifier
                .background(backgroundColor.value)
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.title.value,
                onValueChange = { viewModel.onEvent(AddTaskEvent.TitleChanged(it)) },
                label = { Text(text = "Task title") },
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(colorsList) { item ->
                    ColorItem(
                        color = item,
                        selected = viewModel.color.value == item,
                        modifier = Modifier.size(50.dp)
                    ) {
                        viewModel.onEvent(AddTaskEvent.ColorChanged(item))
                        scope.launch {
                            backgroundColor.animateTo(
                                targetValue = Color(item),
                                animationSpec = tween(
                                    durationMillis = 500
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Due date:")
                    Text(text = viewModel.dueDate.value.formatted())
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = {
                showDatePicker(activity) { newDate ->
                    newDate?.let {
                        viewModel.onEvent(
                            AddTaskEvent.DateChanged(it)
                        )
                    }
                }
            }) {
                Text(text = "Edit Date")
            }
        }

    }
}

private fun showDatePicker(
    activity: AppCompatActivity,
    updatedDate: (Long?) -> Unit
) {
    val picker = MaterialDatePicker.Builder.datePicker().build()
    picker.show(activity.supportFragmentManager, picker.toString())
    picker.addOnPositiveButtonClickListener {
        updatedDate(it)
    }
}