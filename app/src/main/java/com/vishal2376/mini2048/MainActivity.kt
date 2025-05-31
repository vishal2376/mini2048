package com.vishal2376.mini2048

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.vishal2376.mini2048.presentation.navigation.AppNavigation
import com.vishal2376.mini2048.ui.theme.Mini2048Theme
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			KoinContext {
				Mini2048Theme {
					AppNavigation()
				}
			}
		}
	}
}