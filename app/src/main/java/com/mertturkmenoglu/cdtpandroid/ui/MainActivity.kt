package com.mertturkmenoglu.cdtpandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mertturkmenoglu.cdtpandroid.R
import org.jetbrains.anko.design.snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var mAdapter: GreenhouseAdapter
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        initRecyclerView()
        getGreenhouses()

        mSwipeRefreshLayout = findViewById(R.id.homeSwipeRefreshLayout)
        mSwipeRefreshLayout.setOnRefreshListener(::getGreenhouses)

        val homeHelpFab = findViewById<FloatingActionButton>(R.id.homeHelpFab)
        homeHelpFab.setOnClickListener {
            it.snackbar("Help")
        }
    }

    private fun initRecyclerView() {
        val greenhouseRecyclerView = findViewById<RecyclerView>(R.id.homeGreenhousesRecyclerView)
        greenhouseRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = GreenhouseAdapter(this, viewModel)
        greenhouseRecyclerView.adapter = mAdapter
    }

    private fun getGreenhouses() {
        if (this::mSwipeRefreshLayout.isInitialized) {
            mSwipeRefreshLayout.isRefreshing = true
        }

        viewModel.getGreenhouses().observe(this) {
            mAdapter.submitList(it)
            mSwipeRefreshLayout.isRefreshing = false
        }
    }
}