package com.tech.taskscheduler.schedule

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.tech.core.models.Engineer
import com.tech.core.models.WorkDay
import com.tech.core.mvp.BaseMvpActivity
import com.tech.taskscheduler.R
import com.tech.taskscheduler.databinding.ActivityScheduleBinding
import dagger.android.AndroidInjection
import org.parceler.Parcels
import java.lang.StringBuilder

const val KEY_ENGINEERS_LIST = "arg_engineers_list"
class ScheduleActivity: BaseMvpActivity<SchedulePresenter, ScheduleMvp.View>(), ScheduleMvp.View {

    companion object {
        fun startScheduleAdtivity(activity: Activity, list: List<Engineer>) {
            val intent = Intent(activity, ScheduleActivity::class.java)
            intent.putExtra(KEY_ENGINEERS_LIST, Parcels.wrap(list))
            activity.startActivity(intent)
        }
    }
    lateinit var binding: ActivityScheduleBinding
    val engineersList: Lazy<List<Engineer>> = lazy { Parcels.unwrap<List<Engineer>>(intent.getParcelableExtra(KEY_ENGINEERS_LIST))}

    override fun injectAcivity() {
        AndroidInjection.inject(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_schedule, null, false)
        setContentView(binding.root)
        Toast.makeText(this, "${engineersList.value.size}", Toast.LENGTH_SHORT).show()
    }

    override fun showSchedule(schedule: List<WorkDay>) {
        (binding.root as? TextView)?.let {
            it.text = organiseOutput(schedule)
        }
    }

    override fun showError(message: String) {

    }

    override fun onPresenterAttached() {
        presenter.getSchedule(engineersList.value)
    }

    fun organiseOutput(schedule: List<WorkDay>): String {
        val stringBuilder = StringBuilder()
        for(i in schedule.indices) {
            if (schedule[i].engineers.isNotEmpty())
                stringBuilder.appendln("Day ${schedule[i].date} : ${stringifyList(schedule[i].engineers)}")
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