package com.tech.taskscheduler.engineersList

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.tech.core.ApiManager
import com.tech.core.Scheduler
import com.tech.core.models.Engineer
import com.tech.core.mvp.BaseMvpActivity
import com.tech.taskscheduler.R
import dagger.android.AndroidInjection
import java.lang.StringBuilder
import javax.inject.Inject

class EngineersListActivity :
    BaseMvpActivity<EngineersListPresenter, EngineersListMvp.View>(), EngineersListMvp.View {

    lateinit var textView: TextView

    val scheduler = Scheduler(10, 3, 2)

    override fun injectAcivity() {
        AndroidInjection.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_engineers_list)
        textView = findViewById(R.id.text)
    }

    override fun showLoading() {
        textView.text = "Loading..."
    }

    override fun hideLoading() {
        textView.text = ""
    }

    override fun populateResults(engineersList: List<Engineer>) {
        textView.text = organiseOutput(scheduler.scheduleWork(engineersList))
    }

    override fun showError(errorMessage: String) {
        Log.w("EngineersListActivity::", errorMessage)
    }

    override fun onPresenterAttached() {
        presenter.fetchEngineersList()
    }

    fun organiseOutput(schedule: List<List<Engineer>>): String {
        val stringBuilder = StringBuilder()
        for(i in schedule.indices) {
            if (schedule[i].isNotEmpty())
                stringBuilder.appendln("Day ${i+1} : ${stringifyList(schedule[i])}")
        }
        return stringBuilder.toString()
    }

    fun stringifyList(engineers: List<Engineer>): String {
        val stringBuilder = StringBuilder()
        engineers.forEach {
            stringBuilder.append("${it.name},")
        }

        return stringBuilder.toString()
    }
}
