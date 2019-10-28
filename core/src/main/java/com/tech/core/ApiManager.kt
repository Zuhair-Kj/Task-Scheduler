package com.tech.core

import com.tech.core.api.EngineersApiClient
import com.tech.core.models.Engineer
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiManager @Inject constructor(private val engineersApiClient: EngineersApiClient) {
    fun getEngineers(): Single<List<Engineer>> = engineersApiClient.getEngineersList()
}