package com.tech.core.mvp

import android.os.Bundle

interface MvpPresenter<V: MvpView> {
    fun bindView(mvpView: V)
    fun unbindView()
    fun saveState(outState: Bundle)
    fun restoreState(savedState: Bundle)
}