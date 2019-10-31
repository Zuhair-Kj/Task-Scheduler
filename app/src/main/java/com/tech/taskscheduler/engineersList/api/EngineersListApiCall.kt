package com.tech.taskscheduler.engineersList.api

import com.tech.core.ApiManager
import com.tech.core.api.BaseApiCall
import com.tech.core.models.Engineer
import javax.inject.Inject

class EngineersListApiCall @Inject constructor(apiManager: ApiManager)
    : BaseApiCall<List<Engineer>>(apiManager) {
    override fun callSingle() = apiManager.getEngineers()
}