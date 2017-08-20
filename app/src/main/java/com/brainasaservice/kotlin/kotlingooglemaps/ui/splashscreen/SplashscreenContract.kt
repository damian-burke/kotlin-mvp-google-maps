package com.brainasaservice.kotlin.kotlingooglemaps.ui.splashscreen

import com.brainasaservice.kotlin.kotlingooglemaps.ui.BaseContract

/**
 * Created by Damian on 20.08.2017.
 */
class SplashscreenContract {
    interface View : BaseContract.View {
        fun goToMap()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun initialize()
    }
}
