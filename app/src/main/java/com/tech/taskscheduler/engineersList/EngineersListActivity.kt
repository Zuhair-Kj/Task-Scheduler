package com.tech.taskscheduler.engineersList

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.tech.core.ApiManager
import com.tech.core.models.Engineer
import com.tech.core.mvp.BaseMvpActivity
import com.tech.taskscheduler.R
import dagger.android.AndroidInjection
import javax.inject.Inject

class EngineersListActivity :
    BaseMvpActivity<EngineersListPresenter, EngineersListMvp.View>(), EngineersListMvp.View {

    @Inject
    lateinit var apiManager: ApiManager

    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_engineers_list)
        textView = findViewById(R.id.text)
        AndroidInjection.inject(this)
    }

    override fun showLoading() {
        textView.text = "Loading..."
    }

    override fun hideLoading() {
        textView.text = ""
    }

    override fun populateResults(engineersList: List<Engineer>) {
        Toast.makeText(this@EngineersListActivity, engineersList[0].name, Toast.LENGTH_LONG).show()
    }

    override fun showError(errorMessage: String) {
        Log.w("EngineersListActivity::", errorMessage)
    }

    override fun onPresenterAttached() {
        presenter.fetchEngineersList()
    }
}
