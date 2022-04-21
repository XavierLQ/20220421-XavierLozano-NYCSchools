package com.example.a20220421_xavierlozano_nycschools.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a20220421_xavierlozano_nycschools.api.SchoolRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SchoolViewModel(
    private val repository: SchoolRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    /**
     * The [SchoolViewModel] is the viewModel from MVVM architecture, it handles
     * the threads from coroutines.
     * Using [launch] instead of asynch since both threads dont run in parallel
     */

    private val _schoolList: MutableLiveData<StatesSchool> = MutableLiveData(StatesSchool.LOADING)
    val schoolList :LiveData<StatesSchool> get() = _schoolList

    fun getSchools() {
        viewModelScope.launch(ioDispatcher) {
            repository.getSchools().collect {
                _schoolList.postValue(it)
            }
        }
    }

    fun getScoreDetails(id: String) {
        viewModelScope.launch(ioDispatcher) {
            repository.getScores(id).collect {
                _schoolList.postValue(it)
            }
        }
    }
}