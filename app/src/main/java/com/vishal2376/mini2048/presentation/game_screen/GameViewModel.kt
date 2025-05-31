package com.vishal2376.mini2048.presentation.game_screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel : ViewModel() {
	private val _gameState = MutableStateFlow(GameState())
	val gameState: StateFlow<GameState> = _gameState.asStateFlow()

	fun onEvent(event: GameEvent) {
		when (event) {
			is GameEvent.Move -> move(event.direction)
			is GameEvent.Restart -> restart()
		}
	}

	private fun move(direction: Direction) {
		when (direction) {
			Direction.UP -> {}
			Direction.DOWN -> {}
			Direction.LEFT -> {}
			Direction.RIGHT -> {}
		}
	}

	private fun restart() {
		_gameState.value = GameState()
	}
}