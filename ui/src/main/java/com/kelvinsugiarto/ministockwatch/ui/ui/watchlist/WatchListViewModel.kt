package com.kelvinsugiarto.ministockwatch.ui.ui.watchlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelvinsugiarto.ministockwatch.data.common.DataResult
import com.kelvinsugiarto.ministockwatch.data.common.StateResult
import com.kelvinsugiarto.ministockwatch.data.source.model.CryptoModelEnt
import com.kelvinsugiarto.ministockwatch.data.source.model.CryptoRequestEnt
import com.kelvinsugiarto.ministockwatch.data.source.remote.CryptoRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WatchListViewModel(private val repository: CryptoRepository) : ViewModel() {

    var _watchListObservable = MutableLiveData<DataResult<List<CryptoModelEnt>>>()
    val watchListObservable = _watchListObservable as LiveData<DataResult<List<CryptoModelEnt>>>

    var _loadStateObservable = MutableLiveData<StateResult>()
    val loadStateObservable = _loadStateObservable as LiveData<StateResult>

    var cryptoList: MutableList<CryptoModelEnt> = mutableListOf()

    private var page = 1
    private lateinit var state: StateResult

    fun getWatchList(){
        state = StateResult.isLoading
        viewModelScope.launch{
            val result = repository.getCryptoData(CryptoRequestEnt(page,10,"USD"))
            delay(1000L)
            _watchListObservable.postValue(result)
            state = StateResult.isLoaded
        }
    }

    fun resetPage(){
        page = 1
    }

    fun fetchNextPage() {
        if (state == StateResult.isLoaded) {
            state = StateResult.isLoading
            getWatchList()
        }
    }

    fun incrementPageNumber() {
        page++
    }
}