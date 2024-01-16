package com.iapps.practical.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.iapps.practical.databinding.ActivityMainBinding
import com.iapps.practical.ui.adapters.DataAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    private val dataAdapter: DataAdapter by lazy {
        DataAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()
        observe()
    }

    private fun observe() {
        lifecycleScope.launch {
            mainViewModel.uiState.collectLatest {
                binding.progress.isVisible = it is MainUiState.Loading

                if (it is MainUiState.FeedData) {
                    dataAdapter.submitList(it.feeds)
                    binding.tvNoDataFound.isVisible = it.feeds.isEmpty()
                } else if (it is MainUiState.FeedError) {
                    val snack = Snackbar.make(
                        binding.root,
                        it.message,
                        Snackbar.LENGTH_LONG
                    )
                    snack.show()
                }
            }
        }
    }

    private fun setup() {
        binding.rvList.adapter = dataAdapter
    }
}