package com.julia.apd.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.julia.apd.myapplication.dao.Status
import com.julia.apd.myapplication.models.HostModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var hostsAdapter: HostsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this)
        hostsAdapter = HostsAdapter()

        host_list.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = hostsAdapter
        }

        setupModel()
    }

    private fun setupModel() {
        mainViewModel.hosts.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> pingHosts(it.data?.hosts)
                Status.ERROR -> showError(it.message!!)
                Status.LOADING -> showLoading(true)
            }
        })
        mainViewModel.getHosts()
    }

    private fun showLoading(show: Boolean = true) {
        progress.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showError(error: String) {
        showLoading(false)
        Snackbar.make(main_layout, error, Snackbar.LENGTH_LONG).show()
    }

    private fun pingHosts(hosts: List<HostModel>?) {
        mainViewModel.latency.observe(this, Observer {
             hostsAdapter.updateLatency(it.first, it.second)
        })
        showLoading(false)
        hostsAdapter.setItems(hosts)
        if (hosts != null) mainViewModel.ping(hosts)
    }
}
