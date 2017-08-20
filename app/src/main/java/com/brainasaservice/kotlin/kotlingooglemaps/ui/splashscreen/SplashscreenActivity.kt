package com.brainasaservice.kotlin.kotlingooglemaps.ui.splashscreen

import android.support.v7.app.AppCompatActivity
import com.brainasaservice.kotlin.kotlingooglemaps.R
import com.brainasaservice.kotlin.kotlingooglemaps.ui.BaseActivity
import com.brainasaservice.kotlin.kotlingooglemaps.ui.map.MapsActivity
import org.jetbrains.anko.startActivity

/**
 * Created by Damian on 20.08.2017.
 */

class SplashscreenActivity : BaseActivity<SplashscreenContract.View, SplashscreenContract.Presenter>(), SplashscreenContract.View {
    override val contentLayout: Int
        get() = R.layout.activity_splashscreen

    override fun goToMap() {
        startActivity<MapsActivity>()
        finish()
    }

}