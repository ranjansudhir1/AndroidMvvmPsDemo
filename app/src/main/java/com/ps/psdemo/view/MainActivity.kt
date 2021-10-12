package com.ps.psdemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ps.psdemo.databinding.MainActivityBinding
import com.ps.psdemo.utils.ApiState
import com.ps.psdemo.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    private lateinit var mainAdapter: MainAdapter
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        initView()
        initData()
    }

    private fun initData() {
        mainViewModel.getPost()
        lifecycleScope.launchWhenStarted {
            mainViewModel.collectPostStateFlow.collect {
                when (it) {
                    is ApiState.Loading -> {
                        binding.mainRecycleView.isVisible = false
                        binding.mainProgressBar.isVisible = true
                    }
                    is ApiState.Failure -> {
                        binding.mainRecycleView.isVisible = false
                        binding.mainProgressBar.isVisible = false
                        Log.d("Main", "MainActivity:${it.msg}")
                    }
                    is ApiState.Success -> {
                        binding.mainRecycleView.isVisible = true
                        binding.mainProgressBar.isVisible = false
                        mainAdapter.setData(it.data)
                    }
                    is ApiState.Empty -> {
                        //Nothing to do
                    }
                }
            }
        }
    }

    private fun initView() {
        mainAdapter = MainAdapter(ArrayList())
        binding.mainRecycleView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
        }
    }
}