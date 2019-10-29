package com.san4o.just4fun.authenticjobs

import android.app.Application
import com.san4o.just4fun.authenticjobs.repository.JobsRepository
import com.san4o.just4fun.authenticjobs.repository.network.Api
import com.san4o.just4fun.authenticjobs.repository.network.ApiWorker
import com.san4o.just4fun.authenticjobs.ui.JobViewModel
import com.san4o.just4fun.authenticjobs.ui.JobsListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import timber.log.Timber


class App : Application() {


    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())


        startKoin {
            androidContext(this@App)
            modules(listOf(appModule))
        }
    }
}

private val appModule: Module = module {
    single {

        val interceptor = HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://authenticjobs.com")
            .addConverterFactory(ApiWorker.gsonConverter)
            .client(ApiWorker.client)
            .build()

        retrofit.create(Api::class.java)
    }

    single {
        JobsRepository(get())
    }

    viewModel { JobsListViewModel(get()) }

    viewModel { (id: String) -> JobViewModel(get(), id) }
}