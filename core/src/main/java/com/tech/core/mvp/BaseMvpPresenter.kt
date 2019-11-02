package com.tech.core.mvp

import android.os.Bundle

abstract class BaseMvpPresenter<V: MvpView>: MvpPresenter<V> {
    var mvpView: V? = null
    override fun bindView(mvpView: V) {
        this.mvpView = mvpView
    }

    override fun unbindView() {
        mvpView = null
    }

    override fun saveState(outState: Bundle) {}

    override fun restoreState(savedState: Bundle) {}
}