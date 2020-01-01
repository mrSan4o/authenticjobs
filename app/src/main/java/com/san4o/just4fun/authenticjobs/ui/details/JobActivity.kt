package com.san4o.just4fun.authenticjobs.ui.details

import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.san4o.just4fun.authenticjobs.R
import com.san4o.just4fun.authenticjobs.databinding.ActivityJobBinding
import com.san4o.just4fun.authenticjobs.ui.toDateTimeString
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class JobActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityJobBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_job)

        val id = intent.getStringExtra("id") ?: throw IllegalArgumentException("id NOT FOUND")
        val viewModel: JobViewModel by viewModel { parametersOf(id) }

        viewModel.data.observe(this, Observer {
            if (it != null) {

                binding.name.text = it.name
                binding.description.text = Html.fromHtml(it.description)
                binding.company.text = it.company
                binding.type.text = it.type
                binding.date.text = it.postDate.toDateTimeString()

            }
        })

        viewModel.load()
    }
}
