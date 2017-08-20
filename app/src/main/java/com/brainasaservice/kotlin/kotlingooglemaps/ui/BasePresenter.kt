package com.brainasaservice.kotlin.kotlingooglemaps.ui

import com.brainasaservice.kotlin.kotlingooglemaps.model.Dependency
import timber.log.Timber
import java.lang.ref.WeakReference

/**
 * Created by Damian on 20.08.2017.
 */

abstract class BasePresenter<V : BaseContract.View>(val dep: Dependency) : BaseContract.Presenter<V> {

    private var viewRef: WeakReference<V>? = null

    override var view: V? = null
        get() = viewRef?.get()

    override fun hasView(): Boolean {
        return viewRef?.get() != null
    }

    //override fun getView(): V? {
    //    return viewRef?.get()
    //}

    override fun attachView(v: V) {
        Timber.d("attachView(view=%s)", v.toString())
        viewRef = WeakReference(v)
        onViewAttached()
    }

    override fun detachView() {
        viewRef?.clear()
        viewRef = null
        onViewDetached()
    }

    override fun onViewAttached() {
        Timber.d("onViewAttached(presenter=%s, view=%s)", javaClass.simpleName, view?.javaClass?.simpleName)
    }

    override fun onViewDetached() {
        Timber.d("onViewDetached(presenter=%s)", javaClass.simpleName)
    }
}
