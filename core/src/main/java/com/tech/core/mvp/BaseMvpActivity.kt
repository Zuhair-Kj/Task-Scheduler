package com.tech.core.mvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseMvpActivity<P: MvpPresenter<V>, V : MvpView>: AppCompatActivity() {
    @Inject lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectAcivity()
        savedInstanceState?.let {
            presenter.restoreState(it)
        }
    }

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

    open fun onPresenterAttached() {}

    open fun onPresenterDetached() {}

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.saveState(outState)
    }

    abstract fun injectAcivity()
 }