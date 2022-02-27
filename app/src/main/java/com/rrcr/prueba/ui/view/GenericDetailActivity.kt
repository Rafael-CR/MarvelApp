package com.rrcr.prueba.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rrcr.prueba.databinding.ActivityGenericDetailBinding
import com.rrcr.prueba.ui.view.DetailActivity.Companion.MODE_COMICS
import com.rrcr.prueba.ui.view.DetailActivity.Companion.MODE_EVENTS
import com.rrcr.prueba.ui.view.DetailActivity.Companion.MODE_SERIES
import com.rrcr.prueba.ui.view.DetailActivity.Companion.MODE_STORIES
import com.rrcr.prueba.ui.view.adapter.generic.GenericAdapter
import com.rrcr.prueba.ui.viewmodel.GenericDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenericDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGenericDetailBinding
    private val viewModel: GenericDetailViewModel by viewModels()
    var firstVisibleItem = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0
    var previousTotal: Int = 0
    private val visibleThreshold = 5
    private var loading = true
    private var noData = false
    lateinit var idCharacter: String
    var modeType: Int = 0
    lateinit var adapter: GenericAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenericDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initview()

    }

    private fun initview() {
        idCharacter = intent.getStringExtra(DetailActivity.CHARACTER_ID).toString()
        modeType = intent.getIntExtra(DetailActivity.MODE, 0)
        initAdapter()
        initObservers()
        initListeners()
        initMode()
        viewModel.getData(idCharacter, initMode())
    }

    private fun initMode(): String {
        var mode = ""
        when (modeType) {
            MODE_SERIES -> {
                mode = "series"
            }
            MODE_COMICS -> {
                mode = "comics"
            }
            MODE_STORIES -> {
                mode = "stories"
            }
            MODE_EVENTS -> {
                mode = "events"
            }
        }
        return mode
    }

    private fun initListeners() {
        binding.rvDetail.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                visibleItemCount = binding.rvDetail.childCount
                totalItemCount = binding.rvDetail.layoutManager!!.itemCount
                firstVisibleItem =
                    (binding.rvDetail.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false
                        previousTotal = totalItemCount
                    }
                }
                if (!loading && totalItemCount - visibleItemCount
                    <= firstVisibleItem + visibleThreshold
                ) {
                    viewModel.getData(idCharacter, initMode())
                    loading = true
                }
            }
        })
    }

    private fun initObservers() {
        viewModel.dataModelList.observe(this, {
            adapter.notifyDataSetChanged()
        })
        viewModel.isLoading.observe(this, {
            binding.loading.isVisible = it
        })
        viewModel.noData.observe(this, {
            if(it){
                binding.noDataTxt.visibility = View.VISIBLE
            }else{
                binding.noDataTxt.visibility = View.GONE
            }

        })

    }

    private fun initAdapter() {
        binding.rvDetail.layoutManager = LinearLayoutManager(this)
        adapter = GenericAdapter(viewModel.resultados)
        binding.rvDetail.adapter = adapter
    }
}