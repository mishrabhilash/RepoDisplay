package com.abhilashmishra.repodisplay.utility

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider

fun View.setCornerRadius(radiusInPixels: Number) {
    clipToOutline = true
    outlineProvider = object : ViewOutlineProvider() {
        override fun getOutline(view: View?, outline: Outline?) {
            view?.let {
                outline?.setRoundRect(0, 0, view.width, view.height, radiusInPixels.toFloat())
            }
        }
    }
}