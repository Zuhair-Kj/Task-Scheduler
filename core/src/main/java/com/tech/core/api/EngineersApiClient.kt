package com.tech.core.api

import com.tech.core.models.Engineer
import io.reactivex.Single
import retrofit2.http.GET

interface EngineersApiClient {
    @GET("/engineers")
    fun getEngineersList(): Single<List<Engineer>>
}