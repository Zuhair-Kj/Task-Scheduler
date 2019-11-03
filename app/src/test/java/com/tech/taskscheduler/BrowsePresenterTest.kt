package com.tech.taskscheduler

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.tech.core.models.Engineer
import com.tech.taskscheduler.browse.BrowseMvp
import com.tech.taskscheduler.browse.BrowsePresenter
import com.tech.taskscheduler.browse.api.EngineersListApiCall
import com.tech.taskscheduler.browse.api.EngineersListObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class BrowsePresenterTest {
    lateinit var presenter: BrowsePresenter
    @Mock lateinit var engineersListApiCall: EngineersListApiCall
    @Mock lateinit var mvpView: BrowseMvp.View
    @Mock lateinit var engineer: Engineer

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        presenter = BrowsePresenter(engineersListApiCall)
        presenter.bindView(mvpView)
    }

    @Test
    fun shouldCallApi() {
        presenter.fetchEngineersList()
        verify(engineersListApiCall).execute(ArgumentMatchers.any<EngineersListObserver>())
    }

    @Test
    fun shouldReturnCachedResults() {
        presenter.engineersList.add(engineer)
        presenter.fetchEngineersList()
        verify(mvpView).populateResults(Collections.singletonList(engineer))
        verifyZeroInteractions(engineersListApiCall)
    }

    @Test
    fun onResultsFetched() {
        presenter.onEngineersListFetched(listOf(engineer))
        assert(presenter.engineersList.contains(engineer))
        verify(mvpView).populateResults(presenter.engineersList)
    }

}