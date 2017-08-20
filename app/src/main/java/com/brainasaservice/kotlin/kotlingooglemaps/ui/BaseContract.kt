package com.brainasaservice.kotlin.kotlingooglemaps.ui

import android.support.annotation.LayoutRes

/**
 * Created by Damian on 20.08.2017.
 */

class BaseContract {
    interface View {
        @get:LayoutRes val contentLayout: Int
    }

    interface Presenter<V : View> {
        var view: V?

        fun detachView()

        fun hasView() : Boolean

        fun attachView(v: V)

        fun onViewAttached()

        fun onViewDetached()
    }
}
