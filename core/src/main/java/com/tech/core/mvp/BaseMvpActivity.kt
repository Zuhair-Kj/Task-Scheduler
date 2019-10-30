package com.tech.core.mvp

import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

abstract class BaseMvpActivity<P: MvpPresenter<V>, V : MvpView>: AppCompatActivity() {
    @Inject lateinit var presenter: P

    @Suppress("UNCHECKED_CAST")
    override fun onStart() {
        super.onStart()
        presenter.bindView(this as V)
        onPresenterAttached()
    }

    override fun onStop() {
        super.onStop()
        presenter.unbindView()
        onPresenterDetached()
    }

    open fun onPresenterAttached() {

    }

    open fun onPresenterDetached() {

    }
 }