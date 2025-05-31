package com.vishal2376.mini2048.presentation.game_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.vishal2376.mini2048.utils.TileColors


@Composable
fun TileComponent(value: Int) {
	val backgroundColor = TileColors.backgroundColor(value)
	val textColor = TileColors.textColor(value)

	Box(
		modifier = Modifier
			.aspectRatio(1f)
			.clip(RoundedCornerShape(8.dp))
			.background(backgroundColor)
			.padding(4.dp),
		contentAlignment = Alignment.Center
	) {
		if (value != 0) {
			Text(
				text = value.toString(),
				style = MaterialTheme.typography.headlineSmall.copy(
					fontWeight = FontWeight.Bold,
					color = textColor
				)
			)
		}
	}
}