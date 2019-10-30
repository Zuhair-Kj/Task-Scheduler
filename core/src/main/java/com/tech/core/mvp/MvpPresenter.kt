package com.tech.core.mvp

interface MvpPresenter<V: MvpView> {
    fun bindView(mvpView: V)
    fun unbindView()
}