package com.vishal2376.mini2048.di

import com.vishal2376.mini2048.presentation.game_screen.GameViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModules = module {
	viewModelOf(::GameViewModel)
}