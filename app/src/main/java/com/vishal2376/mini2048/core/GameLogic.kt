package com.vishal2376.mini2048.core

import com.vishal2376.mini2048.presentation.game_screen.Direction
import kotlin.random.Random

object GameManager {

	fun spawnRandomTile(grid: List<List<Int>>): List<List<Int>> {
		val emptyCells = grid.flatMapIndexed { rowIndex, row ->
			row.mapIndexedNotNull { colIndex, value ->
				if (value == 0) rowIndex to colIndex else null
			}
		}

		if (emptyCells.isEmpty()) return grid

		val (row, col) = emptyCells.random()
		val newValue = if (Random.nextFloat() < 0.9f) 2 else 4

		return grid.mapIndexed { r, rowList ->
			rowList.mapIndexed { c, cell ->
				if (r == row && c == col) newValue else cell
			}
		}
	}

	fun moveGrid(grid: List<List<Int>>, direction: Direction): Pair<List<List<Int>>, Int> {
		val rotated = when (direction) {
			Direction.LEFT -> grid
			Direction.RIGHT -> grid.map { it.reversed() }
			Direction.UP -> rotateGridLeft(grid)
			Direction.DOWN -> rotateGridRight(grid)
		}

		var score = 0
		val moved = rotated.map { row ->
			val (mergedRow, rowScore) = mergeRow(row)
			score += rowScore
			mergedRow
		}

		val finalGrid = when (direction) {
			Direction.LEFT -> moved
			Direction.RIGHT -> moved.map { it.reversed() }
			Direction.UP -> rotateGridRight(moved)
			Direction.DOWN -> rotateGridLeft(moved)
		}

		return finalGrid to score
	}

	private fun mergeRow(row: List<Int>): Pair<List<Int>, Int> {
		val filtered = row.filter { it != 0 }.toMutableList()
		val merged = mutableListOf<Int>()
		var score = 0
		var i = 0

		while (i < filtered.size) {
			if (i < filtered.size - 1 && filtered[i] == filtered[i + 1]) {
				val sum = filtered[i] * 2
				merged.add(sum)
				score += sum
				i += 2
			} else {
				merged.add(filtered[i])
				i++
			}
		}

		while (merged.size < 4) merged.add(0)
		return merged to score
	}

	private fun rotateGridLeft(grid: List<List<Int>>): List<List<Int>> =
		List(4) { col -> List(4) { row -> grid[row][3 - col] } }

	private fun rotateGridRight(grid: List<List<Int>>): List<List<Int>> =
		List(4) { col -> List(4) { row -> grid[3 - row][col] } }

	fun checkGameOver(grid: List<List<Int>>): Boolean {
		if (grid.any { it.contains(0) }) return false

		for (i in 0..3) {
			for (j in 0..3) {
				val current = grid[i][j]
				if (j < 3 && current == grid[i][j + 1]) return false
				if (i < 3 && current == grid[i + 1][j]) return false
			}
		}
		return true
	}

}