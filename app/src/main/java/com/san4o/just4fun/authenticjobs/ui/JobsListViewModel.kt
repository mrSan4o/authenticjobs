package com.san4o.just4fun.authenticjobs.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.san4o.just4fun.authenticjobs.repository.JobItem
import com.san4o.just4fun.authenticjobs.repository.JobsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class JobsListViewModel(val repository: JobsRepository) : ViewModel() {
    private val _items = MutableLiveData<List<JobItem>>()
    val items: LiveData<List<JobItem>> = _items

    private val _loader = MutableLiveData<Boolean>()
    val loader: LiveData<Boolean> = _loader



    init {
        _loader.value = true
        viewModelScope.launch {
            delay(3000)
            _items.value = repository.findJobs().sortedByDescending { it.postDate }
            _loader.value = false
        }
    }
}