package com.vishal2376.mini2048.presentation.game_screen

data class GameState(
	val grid: List<List<Int>> = emptyGrid(),
	val score: Int = 0,
	val isGameOver: Boolean = false,
)

fun emptyGrid(): List<List<Int>> = List(4) { List(4) { 0 } }