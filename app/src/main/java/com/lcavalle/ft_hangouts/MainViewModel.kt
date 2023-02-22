package com.lcavalle.ft_hangouts

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val pauseTimeName = "pausedTimeMs"
    private val isInHomeScreen = "homeScreen"

    val statusBarColorState: MutableStateFlow<Color> = MutableStateFlow(Color.Transparent)

    fun setStatusBarColor(color: Color) {
        statusBarColorState.value = color
    }

    val pauseTimeState: StateFlow<Long> =
        savedStateHandle.getStateFlow(pauseTimeName, 0)
    val isHomeScreenState: StateFlow<Boolean> =
        savedStateHandle.getStateFlow(isInHomeScreen, false)

    fun setIsHome(isHome: Boolean) {
        savedStateHandle[isInHomeScreen] = isHome
    }

    fun setPauseTime() {
        savedStateHandle[pauseTimeName] = System.currentTimeMillis()
    }
}