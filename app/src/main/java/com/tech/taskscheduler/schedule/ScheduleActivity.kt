package com.tech.taskscheduler.schedule

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.tech.core.models.Engineer
import com.tech.core.mvp.BaseMvpActivity
import com.tech.taskscheduler.R
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
    val engineersList: Lazy<List<Engineer>> = lazy { Parcels.unwrap<List<Engineer>>(intent.getParcelableExtra(KEY_ENGINEERS_LIST))}

    override fun injectAcivity() {
        AndroidInjection.inject(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
        Toast.makeText(this, "${engineersList.value.size}", Toast.LENGTH_SHORT).show()
    }

    override fun showSchedule(schedule: List<List<Engineer>>) {

    }

    override fun showError(message: String) {

    }
}