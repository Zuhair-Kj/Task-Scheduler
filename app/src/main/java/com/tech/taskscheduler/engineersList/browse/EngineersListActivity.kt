package com.tech.taskscheduler.engineersList.browse

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tech.core.Scheduler
import com.tech.core.models.Engineer
import com.tech.core.mvp.BaseMvpActivity
import com.tech.taskscheduler.R
import com.tech.taskscheduler.databinding.ActivityEngineersListBinding
import dagger.android.AndroidInjection
import java.lang.StringBuilder

class EngineersListActivity :
    BaseMvpActivity<EngineersListPresenter, EngineersListMvp.View>(),
    EngineersListMvp.View {

    lateinit var binding: ActivityEngineersListBinding
    lateinit var progressBar: ProgressBar
    lateinit var recyclerView: RecyclerView
    lateinit var engineersAdapter: EngineersAdapter
    private val cellSpacing by lazy { this.resources.getDimension(R.dimen.margin_small) }

    val scheduler = Scheduler(10, 3, 2)

    override fun injectAcivity() {
        AndroidInjection.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_engineers_list, null, false)
        setContentView(binding.root)
        progressBar = binding.progressCircular
        recyclerView = binding.recyclerView
        engineersAdapter =
            EngineersAdapter(mutableListOf(), this)
        recyclerView.layoutManager = GridLayoutManager(
            this,
            2,
            RecyclerView.VERTICAL,
            false
        )
        recyclerView.adapter = engineersAdapter
        recyclerView.addItemDecoration(
            EngineersListItemsDecorator(
                cellSpacing.toInt()
            )
        )
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun populateResults(engineersList: List<Engineer>) {
        engineersAdapter.addItems(engineersList)
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
