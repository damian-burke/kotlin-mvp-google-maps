package com.brainasaservice.kotlin.kotlingooglemaps.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Damian on 20.08.2017.
 */

abstract class BaseFragment<V : BaseContract.View, P : BaseContract.Presenter<V>>
    : DaggerFragment(), BaseContract.View {

    @Inject
    internal lateinit var presenter: P

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.d("onCreateView(contentLayout=%d)", contentLayout)
        return inflater!!.inflate(contentLayout, container, false)
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume(view=%s)", javaClass.simpleName)
        attachView()
    }

    override fun onPause() {
        super.onPause()
        Timber.d("onPause(view=%s)", javaClass.simpleName)
        detachView()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated(view=%s)", javaClass.simpleName)
        attachView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("onDestroyView(view=%s)", javaClass.simpleName)
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
