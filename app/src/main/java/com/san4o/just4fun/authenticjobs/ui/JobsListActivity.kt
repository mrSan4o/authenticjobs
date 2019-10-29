package com.san4o.just4fun.authenticjobs.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.san4o.just4fun.authenticjobs.R
import com.san4o.just4fun.authenticjobs.databinding.ViewHolderJobListItemBinding
import com.san4o.just4fun.authenticjobs.repository.JobItem
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class JobsListActivity : AppCompatActivity(), NavigateOnItemListener {
    val viewModel: JobsListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val jobsListAdapter = JobsListAdapter(this)
        list.adapter = jobsListAdapter



        viewModel.items.observe(this, Observer {
            jobsListAdapter.refreshItems(it ?: emptyList())
        })
    }


    override fun navigateItem(id: Long) {
        val intent = Intent(this, JobActivity::class.java)
        intent.putExtra("id", id.toString())
        startActivity(intent)
    }
}

interface NavigateOnItemListener {
    fun navigateItem(id: Long)
}

class JobsListAdapter(
    private val listener: NavigateOnItemListener
) : RecyclerView.Adapter<JobsListViewHolder>() {

    val items = ArrayList<JobItem>()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsListViewHolder {
        return JobsListViewHolder.create(parent, listener)
    }

    override fun onBindViewHolder(holder: JobsListViewHolder, position: Int) {
        val item = items[position]
        holder.setModel(item)
    }

    fun refreshItems(list: List<JobItem>) {
        this.items.clear()
        this.items.addAll(list)

        notifyDataSetChanged()
    }
}

class JobsListViewHolder(
    private val binding: ViewHolderJobListItemBinding,
    private val listener: NavigateOnItemListener
) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(group: ViewGroup, listener: NavigateOnItemListener): JobsListViewHolder {
            val binding: ViewHolderJobListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(group.context),
                R.layout.view_holder_job_list_item,
                group,
                false
            )
            return JobsListViewHolder(binding, listener)

        }
    }

    fun setModel(item: JobItem) {
        binding.name.text = item.name
        binding.dateView.text = item.postDate.toDateTimeString()
        binding.cardLayout.setOnClickListener {
            listener.navigateItem(item.id)
        }
    }
}

fun Date.toDateTimeString(): String {
    return SimpleDateFormat("dd.MM.yyyy HH:mm").format(this)
}
