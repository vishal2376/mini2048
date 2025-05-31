package com.vishal2376.mini2048

import android.app.Application
import com.vishal2376.mini2048.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GameApplication : Application() {
	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidContext(this@GameApplication)
			modules(appModules)
		}
	}
}