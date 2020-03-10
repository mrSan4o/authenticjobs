package com.san4o.just4fun.authenticjobs.koin

import com.san4o.just4fun.authenticjobs.repository.JobsRepository
import com.san4o.just4fun.authenticjobs.repository.network.Api
import com.san4o.just4fun.authenticjobs.repository.network.ApiWorker
import com.san4o.just4fun.authenticjobs.ui.details.JobViewModel
import com.san4o.just4fun.authenticjobs.ui.list.JobsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {
    single {

        val retrofit = ApiWorker.retrofit

        retrofit.create(Api::class.java)
    }

    single {
        JobsRepository(get())
    }

    viewModel { JobsListViewModel(get()) }

    viewModel { (id: String) ->
        JobViewModel(
            get(),
            id
        )
    }
}