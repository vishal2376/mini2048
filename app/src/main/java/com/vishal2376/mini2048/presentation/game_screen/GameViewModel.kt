package com.vishal2376.mini2048.presentation.game_screen

import androidx.lifecycle.ViewModel
import com.vishal2376.mini2048.core.GameManager.checkGameOver
import com.vishal2376.mini2048.core.GameManager.moveGrid
import com.vishal2376.mini2048.core.GameManager.spawnRandomTile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel : ViewModel() {
	private val _gameState = MutableStateFlow(GameState())
	val gameState: StateFlow<GameState> = _gameState.asStateFlow()

	init {
		startGame()
	}

	fun onEvent(event: GameEvent) {
		when (event) {
			is GameEvent.Move -> move(event.direction)
			is GameEvent.Restart -> restart()
		}
	}

	private fun startGame() {
		val initialGrid = spawnRandomTile(spawnRandomTile(emptyGrid()))
		_gameState.value = gameState.value.copy(grid = initialGrid)
	}

	private fun move(direction: Direction) {
		val oldGrid = gameState.value.grid
		val (newGrid, scoreGain) = moveGrid(oldGrid, direction)

		if (oldGrid != newGrid) {
			val withNewTile = spawnRandomTile(newGrid)
			_gameState.value = gameState.value.copy(
				grid = withNewTile,
				score = gameState.value.score + scoreGain,
				isGameOver = checkGameOver(withNewTile)
			)
		}
	}

	private fun restart() {
		_gameState.value = GameState()
		startGame()
	}
}
