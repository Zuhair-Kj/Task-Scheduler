package com.tech.taskscheduler.engineersList.browse

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tech.core.models.Engineer
import com.tech.core.mvp.BaseMvpActivity
import com.tech.taskscheduler.R
import com.tech.taskscheduler.databinding.ActivityEngineersListBinding
import com.tech.taskscheduler.schedule.ScheduleActivity
import dagger.android.AndroidInjection

class EngineersListActivity :
    BaseMvpActivity<EngineersListPresenter, EngineersListMvp.View>(),
    EngineersListMvp.View, View.OnClickListener {

    lateinit var binding: ActivityEngineersListBinding
    lateinit var progressBar: ProgressBar
    lateinit var recyclerView: RecyclerView
    lateinit var engineersAdapter: EngineersAdapter
    lateinit var fabButton: FloatingActionButton
    private val cellSpacing by lazy { this.resources.getDimension(R.dimen.margin_small) }

    override fun injectAcivity() {
        AndroidInjection.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_engineers_list, null, false)
        setContentView(binding.root)
        progressBar = binding.progressCircular
        recyclerView = binding.recyclerView
        fabButton = binding.fab
        engineersAdapter = EngineersAdapter(mutableListOf(), this)
        fabButton.setOnClickListener(this)
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
        fabButton.hide()
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun populateResults(engineersList: List<Engineer>) {
        engineersAdapter.clear()
        engineersAdapter.addItems(engineersList)
        fabButton.show()
    }

    override fun showError(errorMessage: String) {
        Log.w("EngineersListActivity::", errorMessage)
    }

    override fun onPresenterAttached() {
        presenter.fetchEngineersList()
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.fab -> ScheduleActivity.startScheduleAdtivity(this, presenter.engineersList)
        }
    }
}
