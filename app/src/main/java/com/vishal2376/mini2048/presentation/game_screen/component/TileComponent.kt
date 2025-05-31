package com.vishal2376.mini2048.presentation.game_screen.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.vishal2376.mini2048.ui.theme.TileSuper
import com.vishal2376.mini2048.utils.TileColors


@Composable
fun TileComponent(value: Int) {
	val targetScale = if (value != 0) 1f else 0f

	val tileScale by animateFloatAsState(
		targetValue = targetScale,
		animationSpec = tween(
			durationMillis = 400,
			easing = FastOutSlowInEasing
		),
		label = "Tile Scale"
	)

	val tileAlpha by animateFloatAsState(
		targetValue = if (value != 0) 1f else 0f,
		animationSpec = tween(durationMillis = 600),
		label = "Tile Alpha"
	)

	Box(
		modifier = Modifier
			.clip(RoundedCornerShape(16.dp))
			.background(TileSuper)
			.graphicsLayer {
				scaleX = tileScale
				scaleY = tileScale
				alpha = tileAlpha
			}
			.aspectRatio(1f)
			.background(TileColors.backgroundColor(value), RoundedCornerShape(16.dp))
			.padding(4.dp),
		contentAlignment = Alignment.Center
	) {
		if (value != 0) {
			Text(
				text = value.toString(),
				style = MaterialTheme.typography.headlineSmall.copy(
					fontWeight = FontWeight.Bold,
					color = TileColors.textColor(value)
				)
			)
		}
	}
}