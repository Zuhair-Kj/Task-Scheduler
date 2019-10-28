package com.tech.taskscheduler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.tech.core.ApiManager
import com.tech.core.models.Engineer
import dagger.android.AndroidInjection
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var apiManager: ApiManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
        apiManager.getEngineers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: SingleObserver<List<Engineer>> {
                override fun onError(e: Throwable) {
                    Log.w("MainActivity::", e.message ?: "error")
                }

                override fun onSubscribe(d: Disposable) {}
                override fun onSuccess(t: List<Engineer>) {
                    Toast.makeText(this@MainActivity, t[0].name, Toast.LENGTH_LONG).show()
                }
            })
    }
}
