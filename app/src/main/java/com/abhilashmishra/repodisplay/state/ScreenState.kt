package com.abhilashmishra.repodisplay.state

sealed class ScreenState {
    object NO_DATA : ScreenState()
    object LOADING : ScreenState()
    object ERROR_GENERAL : ScreenState()
    object ERROR_NO_NETWORK : ScreenState()
    object SUCCESS_DATA : ScreenState()
}