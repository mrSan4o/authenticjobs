package com.san4o.just4fun.authenticjobs.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.san4o.just4fun.authenticjobs.repository.Job
import com.san4o.just4fun.authenticjobs.repository.JobsRepository
import kotlinx.coroutines.launch

class JobViewModel(
    private val repository: JobsRepository,
    private val id: String
) : ViewModel() {

    val data = MutableLiveData<Job>()

    init {
        viewModelScope.launch {
            val job = repository.getJob(id)
            data.postValue(job)
        }
    }
}