package com.brainasaservice.kotlin.kotlingooglemaps.util

import android.view.View

/**
 * Created by Damian on 20.08.2017.
 */

/**
 * Performs a given action when a layout change happens.
 */
inline fun View.onLayoutChange(crossinline action: View.() -> Unit) {
    addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
        override fun onLayoutChange(v: View, left: Int, top: Int,
                                    right: Int, bottom: Int,
                                    oldLeft: Int, oldTop: Int,
                                    oldRight: Int, oldBottom: Int) {
            removeOnLayoutChangeListener(this)
            action()
        }
    })
}
