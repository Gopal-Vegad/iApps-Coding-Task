package com.iapps.practical.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iapps.practical.domain.IMainRepository
import com.iapps.practical.model.FeedModel
import com.iapps.practical.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: IMainRepository
) : ViewModel() {

    private val _uiState = MutableSharedFlow<MainUiState>(1)
    val uiState = _uiState.asSharedFlow()

    private var dataFetchJob: Job? = null
    private var dataCollectJob: Job? = null

    init {
        getFeedData()
        collectDataStream()
    }

    private fun getFeedData() {
        dataFetchJob = viewModelScope.launch {
            _uiState.emit(MainUiState.Loading)

            when(val result = repository.getFeeds()) {
                is Resource.Error -> {
                    result.message?.let { _uiState.emit(MainUiState.FeedError(it)) }
                }
                else -> {
                    collectDataStream()
                }
            }
        }
    }

    private fun collectDataStream() {
        dataCollectJob?.cancel(null)
        dataCollectJob = viewModelScope.launch {
            repository.feedStream.collectLatest {
                if(dataFetchJob?.isActive == true && it.isEmpty()) {
                    _uiState.emit(MainUiState.Loading)
                }else {
                    _uiState.emit(MainUiState.FeedData(it))
                }
            }
        }
    }
}

sealed class MainUiState {
    data object Loading: MainUiState()

    data class FeedData(val feeds: List<FeedModel>): MainUiState()

    data class FeedError(val message: String): MainUiState()
}