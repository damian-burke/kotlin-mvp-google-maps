package com.brainasaservice.kotlin.kotlingooglemaps.ui.splashscreen

import com.brainasaservice.kotlin.kotlingooglemaps.model.Dependency
import com.brainasaservice.kotlin.kotlingooglemaps.ui.BasePresenter
import io.reactivex.Flowable
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * Created by Damian on 20.08.2017.
 */

class SplashscreenPresenter(dependency: Dependency) : BasePresenter<SplashscreenContract.View>(dependency), SplashscreenContract.Presenter {
    override fun initialize() {

    }

    override fun onViewAttached() {
        super.onViewAttached()

        Flowable.just("we are live")
                .delay(2, TimeUnit.SECONDS)
                .subscribe({ result ->
                    Timber.d("Initialization result is %s", result)
                    view?.goToMap()
                }, { error ->
                    Timber.e(error, "Error while initializing.")
                    view?.goToMap()
                })
    }
}