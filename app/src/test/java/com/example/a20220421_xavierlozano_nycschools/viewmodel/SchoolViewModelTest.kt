package com.example.a20220421_xavierlozano_nycschools.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a20220421_xavierlozano_nycschools.api.SchoolRepository
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SchoolViewModelTest {

    @get:Rule val rule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private val mockRepository = mockk<SchoolRepository>(relaxed = true)

    private lateinit var testObject: SchoolViewModel

    @Before
    fun startup() {
        Dispatchers.setMain(testDispatcher)
        testObject = SchoolViewModel(mockRepository, testDispatcher)
    }

    @After
    fun shutdown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }

    @Test
    fun `get schools when server retrieves data return success response`() {
        every { mockRepository.getSchools() } returns flowOf(StatesSchool.SchoolsSuccess(listOf(
            mockk {
                every { dbn } returns "id"
            })))
        val stateListHelper = mutableListOf<StatesSchool>()
        testObject.schoolList.observeForever {
            stateListHelper.add(it)
        }

        testObject.getSchools()

        val result = stateListHelper[1] as StatesSchool.SchoolsSuccess
        assertEquals("id", result.results.first().dbn)
    }

    @Test
    fun `get schools when server retrieves data return error response`() {
        every { mockRepository.getSchools() } returns flowOf(StatesSchool.ERROR(Exception("Error Loading the Data")))
        val stateListHelper = mutableListOf<StatesSchool>()
        testObject.schoolList.observeForever {
            stateListHelper.add(it)
        }
        testObject.getSchools()
        assertThat(stateListHelper[1]).isInstanceOf(StatesSchool.ERROR::class.java)
    }

    @Test
    fun `get schools when server retrieves data return Loading response`() {
        every { mockRepository.getSchools() } returns flowOf(StatesSchool.LOADING)
        val stateListHelper = mutableListOf<StatesSchool>()
        testObject.schoolList.observeForever {
            stateListHelper.add(it)
        }
        testObject.getSchools()
        assertThat(stateListHelper[1]).isInstanceOf(StatesSchool.LOADING::class.java)
    }

}