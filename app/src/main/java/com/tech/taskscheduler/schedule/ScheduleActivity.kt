package com.tech.taskscheduler.schedule

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.tech.core.models.Engineer
import com.tech.core.models.WorkDay
import com.tech.core.mvp.BaseMvpActivity
import com.tech.taskscheduler.R
import com.tech.taskscheduler.databinding.ActivityScheduleBinding
import dagger.android.AndroidInjection
import org.parceler.Parcels

const val KEY_ENGINEERS_LIST = "arg_engineers_list"
class ScheduleActivity: BaseMvpActivity<SchedulePresenter, ScheduleMvp.View>(), ScheduleMvp.View {

    companion object {
        fun startScheduleAdtivity(activity: Activity, list: List<Engineer>) {
            val intent = Intent(activity, ScheduleActivity::class.java)
            intent.putExtra(KEY_ENGINEERS_LIST, Parcels.wrap(list))
            activity.startActivity(intent)
        }
    }
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityScheduleBinding
    private lateinit var coordinatorLayout: CoordinatorLayout
    private val engineersList: Lazy<List<Engineer>> = lazy { Parcels.unwrap<List<Engineer>>(intent.getParcelableExtra(KEY_ENGINEERS_LIST))}
    private lateinit var workDayAdapter: WorkDayAdapter
    private val cellSpacing by lazy { this.resources.getDimension(R.dimen.margin_medium) }
    private val lazyDecorator = lazy { WorkDayItemDecorator(cellSpacing.toInt(), getRecyclerViewOrientation()) }

    override fun injectAcivity() {
        AndroidInjection.inject(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_schedule, null, false)
        setContentView(binding.root)

        recyclerView = binding.recyclerView as RecyclerView
        workDayAdapter= WorkDayAdapter(mutableListOf(), this)
        coordinatorLayout = binding.coordinatorLayout

        recyclerView.let {
            it.adapter = workDayAdapter
            it.layoutManager = LinearLayoutManager(this, getRecyclerViewOrientation(), false)
            it.addItemDecoration(lazyDecorator.value)
        }
    }

    override fun showSchedule(schedule: List<WorkDay>) {
        workDayAdapter.clear()
        workDayAdapter.addItems(schedule)
    }

    override fun showError(message: String) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show()
    }

    override fun onPresenterAttached() {
        presenter.getSchedule(engineersList.value)
    }

    private fun getRecyclerViewOrientation() : Int  =
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) RecyclerView.HORIZONTAL
        else RecyclerView.VERTICAL
}