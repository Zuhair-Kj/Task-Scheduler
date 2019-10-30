package com.tech.core.mvp

abstract class BaseMvpPresenter<V: MvpView>: MvpPresenter<V> {
    var mvpView: V? = null
    override fun bindView(mvpView: V) {
        this.mvpView = mvpView
    }

    override fun unbindView() {
        mvpView = null
    }
}