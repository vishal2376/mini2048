package com.vishal2376.mini2048.utils

import androidx.compose.ui.graphics.Color
import com.vishal2376.mini2048.ui.theme.Tile1024
import com.vishal2376.mini2048.ui.theme.Tile128
import com.vishal2376.mini2048.ui.theme.Tile16
import com.vishal2376.mini2048.ui.theme.Tile2
import com.vishal2376.mini2048.ui.theme.Tile2048
import com.vishal2376.mini2048.ui.theme.Tile256
import com.vishal2376.mini2048.ui.theme.Tile32
import com.vishal2376.mini2048.ui.theme.Tile4
import com.vishal2376.mini2048.ui.theme.Tile512
import com.vishal2376.mini2048.ui.theme.Tile64
import com.vishal2376.mini2048.ui.theme.Tile8
import com.vishal2376.mini2048.ui.theme.TileSuper
import com.vishal2376.mini2048.ui.theme.TileTextDark
import com.vishal2376.mini2048.ui.theme.TileTextLight

object TileColors {
	private val tileMap = mapOf(
		0 to TileSuper,
		2 to Tile2,
		4 to Tile4,
		8 to Tile8,
		16 to Tile16,
		32 to Tile32,
		64 to Tile64,
		128 to Tile128,
		256 to Tile256,
		512 to Tile512,
		1024 to Tile1024,
		2048 to Tile2048
	)

	fun backgroundColor(value: Int): Color {
		return tileMap[value] ?: TileSuper
	}

	fun textColor(value: Int): Color {
		return if (value <= 4) TileTextDark else TileTextLight
	}
}
