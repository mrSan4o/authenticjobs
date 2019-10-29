package com.san4o.just4fun.authenticjobs.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.san4o.just4fun.authenticjobs.repository.JobItem
import com.san4o.just4fun.authenticjobs.repository.JobsRepository
import kotlinx.coroutines.launch

class JobsListViewModel(val repository: JobsRepository) : ViewModel() {
    val _items = MutableLiveData<List<JobItem>>()

    val items: LiveData<List<JobItem>> = _items



    init {

        viewModelScope.launch {
            _items.value = repository.findJobs().sortedByDescending { it.postDate }
        }
    }

}