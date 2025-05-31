package com.vishal2376.mini2048.presentation.game_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vishal2376.mini2048.presentation.game_screen.component.TileComponent
import com.vishal2376.mini2048.ui.theme.Mini2048Theme
import com.vishal2376.mini2048.utils.Constants
import kotlin.math.abs

@Composable
fun GameScreenRoot(viewModel: GameViewModel) {
	val gameState by viewModel.gameState.collectAsState()
	GameScreen(
		gameState = gameState,
		onEvent = viewModel::onEvent
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
	gameState: GameState,
	onEvent: (GameEvent) -> Unit
) {
	Scaffold(
		topBar = {
			TopAppBar(
				colors = TopAppBarDefaults.topAppBarColors(
					containerColor = MaterialTheme.colorScheme.background,
					titleContentColor = MaterialTheme.colorScheme.onBackground
				),
				title = { Text("Mini 2048") },
				actions = {
					IconButton(onClick = { onEvent(GameEvent.Restart) }) {
						Icon(imageVector = Icons.Default.Refresh, contentDescription = "Restart")
					}
				}
			)
		}
	) { innerPadding ->
		Column(
			modifier = Modifier
				.fillMaxSize()
				.background(MaterialTheme.colorScheme.background)
				.padding(innerPadding)
				.padding(16.dp),
			verticalArrangement = Arrangement.spacedBy(24.dp),
		) {

			// Score
			Text(
				text = "Score: ${gameState.score}",
				style = MaterialTheme.typography.headlineMedium,
				modifier = Modifier.align(Alignment.CenterHorizontally)
			)

			// Grid
			LazyVerticalGrid(
				columns = GridCells.Fixed(4),
				modifier = Modifier
					.clip(RoundedCornerShape(24.dp))
					.pointerInput(Unit) {
						detectDragGestures { change, dragAmount ->
							val (x, y) = dragAmount
							if (abs(x) > abs(y)) {
								when {
									x > Constants.SWIPE_THRESHOLD -> {
										onEvent(GameEvent.Move(Direction.RIGHT))
									}

									x < -Constants.SWIPE_THRESHOLD -> {
										onEvent(GameEvent.Move(Direction.LEFT))
									}
								}
							} else {
								when {
									y > Constants.SWIPE_THRESHOLD -> {
										onEvent(GameEvent.Move(Direction.DOWN))
									}

									y < -Constants.SWIPE_THRESHOLD -> {
										onEvent(GameEvent.Move(Direction.UP))
									}
								}
							}
							change.consume()
						}
					}
					.fillMaxWidth()
					.aspectRatio(1f)
					.background(MaterialTheme.colorScheme.primaryContainer),
				contentPadding = PaddingValues(12.dp),
				verticalArrangement = Arrangement.spacedBy(8.dp),
				horizontalArrangement = Arrangement.spacedBy(8.dp),
				userScrollEnabled = false
			) {
				items(gameState.grid.flatten()) { cellValue ->
					TileComponent(cellValue)
				}
			}

			// Game Over
			if (gameState.isGameOver) {
				Text(
					text = "Game Over",
					style = MaterialTheme.typography.headlineLarge,
					color = Color.Red,
					modifier = Modifier.align(Alignment.CenterHorizontally)
				)
			}
		}
	}
}

@Preview
@Composable
private fun GameScreenPreview() {
	Mini2048Theme {
		GameScreen(GameState(), {})
	}
}