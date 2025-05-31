package com.vishal2376.mini2048.presentation.game_screen

enum class Direction {
	UP, DOWN, LEFT, RIGHT
}

sealed class GameEvent {
	data class Move(val direction: Direction) : GameEvent()
	data object Restart : GameEvent()
}