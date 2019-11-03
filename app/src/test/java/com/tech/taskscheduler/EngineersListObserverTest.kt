package com.tech.taskscheduler

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.tech.core.models.Engineer
import com.tech.taskscheduler.browse.BrowseMvp
import com.tech.taskscheduler.browse.BrowsePresenter
import com.tech.taskscheduler.browse.api.EngineersListObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException

@RunWith(MockitoJUnitRunner::class)
class EngineersListObserverTest {
    lateinit var observer: EngineersListObserver

    @Mock lateinit var presenter: BrowsePresenter
    @Mock lateinit var view: BrowseMvp.View
    val list = listOf<Engineer>(Mockito.mock(Engineer::class.java))
    val error = RuntimeException("Error")

    @Before
    fun init() {
        observer = EngineersListObserver(view, presenter)
    }

    @Test
    fun onSuccess() {
        observer.onSuccess(list)
        verify(view).hideLoading()
        verify(presenter).onEngineersListFetched(list)
        verifyZeroInteractions(view)
    }

    @Test
    fun onError() {
        observer.onError(error)
        verify(view).showError("Error")
    }

    // onStart is protected method and cannot be tested
}