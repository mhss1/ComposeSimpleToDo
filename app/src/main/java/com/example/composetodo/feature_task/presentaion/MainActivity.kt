package com.example.composetodo.feature_task.presentaion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composetodo.feature_task.presentaion.add_task.AddTaskScreen
import com.example.composetodo.feature_task.presentaion.tasks.TasksScreen
import com.example.composetodo.feature_task.presentation.util.Screen
import com.example.composetodo.ui.theme.ComposeToDoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeToDoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.TasksScreen.route){

                        composable(route = Screen.TasksScreen.route){
                            TasksScreen(navController = navController)
                        }
                        composable(route = Screen.AddTaskScreen.route){
                            AddTaskScreen(navController = navController)
                        }

                    }
                }
            }
        }
    }
}