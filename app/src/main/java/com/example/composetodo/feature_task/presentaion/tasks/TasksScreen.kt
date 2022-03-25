package com.example.composetodo.feature_task.presentaion.tasks

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composetodo.R
import com.example.composetodo.feature_task.presentation.tasks.components.TaskCard
import com.example.composetodo.feature_task.presentation.util.Screen
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TasksScreen(
    navController: NavController,
    viewModel: TasksViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val tasks = viewModel.allTasks.collectAsState(emptyList())

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is TasksViewModel.UiEvent.ShowSnackBar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = "Task marked as done!",
                        actionLabel = "Undo"
                    )
                    if (result == SnackbarResult.ActionPerformed)
                        viewModel.onEvent(TaskEvent.RestoreTask)
                }
            }
        }
    }
    Scaffold(
        Modifier
            .defaultMinSize()
            .padding(12.dp),
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screen.AddTaskScreen.route) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "add task"
                )
            }
        }) {
        Column(Modifier.fillMaxSize()) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(tasks.value, key = { it.id }) { task ->
                    var checked by remember { mutableStateOf(false) }
                    TaskCard(Modifier.animateItemPlacement(), task, checked = checked) {
                        checked = !checked
                        if (checked) {
                            viewModel.onEvent(TaskEvent.DeleteTask(task))
                        } else
                            viewModel.onEvent(TaskEvent.CancelDelete)
                    }
                }
            }
        }
        if (tasks.value.isEmpty())
            Text(
                text = "No Tasks added yet",
                style = MaterialTheme.typography.body1
            )
    }
}