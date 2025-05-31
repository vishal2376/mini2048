package com.vishal2376.mini2048.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vishal2376.mini2048.presentation.game_over_screen.GameOverScreen
import com.vishal2376.mini2048.presentation.game_screen.GameScreen

@Composable
fun AppNavigation() {
	val navController = rememberNavController()

	NavHost(navController = navController, startDestination = Screen.GameScreen.name) {
		composable(Screen.GameScreen.name) {
			GameScreen()
		}
		composable(Screen.GameOverScreen.name) {
			GameOverScreen()
		}
	}
}