package com.brainasaservice.kotlin.kotlingooglemaps.ui

import android.os.Bundle
import com.brainasaservice.kotlin.kotlingooglemaps.model.Dependency
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Damian on 20.08.2017.
 */

abstract class BaseActivity<V : BaseContract.View, P : BaseContract.Presenter<V>>
    : DaggerAppCompatActivity(), BaseContract.View {

    @Inject
    internal lateinit var dep: Dependency

    @Inject
    internal lateinit var presenter: P

    fun onBeforeCreate() {
        Timber.d("onBeforeCreate()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBeforeCreate()
        setContentView(contentLayout)
    }

    override fun onStart() {
        super.onStart()
        attachView()
    }

    override fun onStop() {
        super.onStop()
        detachView()
    }

    private val isAttached: Boolean
        get() = presenter.view?.equals(this) ?: false

    private fun attachView() {
        if (!isAttached) {
            presenter.attachView(this as V)
        }
    }

    private fun detachView() {
        if (isAttached) {
            presenter.detachView()
        }
    }
}
