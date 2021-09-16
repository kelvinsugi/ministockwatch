package com.kelvinsugiarto.ministockwatch.ui.ui.watchlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kelvinsugiarto.ministockwatch.data.common.DataResult
import com.kelvinsugiarto.ministockwatch.data.common.StateResult
import com.kelvinsugiarto.ministockwatch.data.source.model.CryptoModelEnt
import com.kelvinsugiarto.ministockwatch.ui.R
import com.kelvinsugiarto.ministockwatch.ui.ui.main.MainActivity
import com.kelvinsugiarto.ministockwatch.ui.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WatchListFragment : Fragment() {

    companion object {
        fun newInstance() = WatchListFragment()
    }
    private val viewModel by viewModel<WatchListViewModel>()
    lateinit var watchListAdapter: WatchListAdapter
    lateinit var watchListLayoutView : View
    lateinit var recyclerViewWatchList : RecyclerView
    lateinit var progressBarLoad:ProgressBar
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var textViewErrorMessage:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        watchListLayoutView = inflater.inflate(R.layout.watch_list_fragment, container, false)
        recyclerViewWatchList = watchListLayoutView.findViewById(R.id.recycler_view_crypto_data)
        progressBarLoad = watchListLayoutView.findViewById(R.id.progress_bar_loading)
        swipeRefreshLayout = watchListLayoutView.findViewById(R.id.pull_to_refresh)
        textViewErrorMessage =  watchListLayoutView.findViewById(R.id.text_view_error_message)
        return watchListLayoutView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).supportActionBar!!.title = ""
        (activity as MainActivity).setToolbarTitle("Watchlist")
        (activity as MainActivity).showBottomNavigation()

        setUpObserver()
        setupView()
        viewModel.getWatchList()
    }


    private fun setupView(){
        recyclerViewWatchList.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        watchListAdapter = WatchListAdapter {
            viewModel.fetchNextPage()
            progressBarLoad.showSlideUp()
        }

        recyclerViewWatchList.adapter = watchListAdapter

        swipeRefreshLayout.setOnRefreshListener {
            textViewErrorMessage.setGone()
            recyclerViewWatchList.setGone()
            watchListAdapter.submitList(null)
            viewModel.resetPage()
            viewModel.getWatchList()
        }
    }


    private fun setUpObserver() {
        viewModel.watchListObservable.observe(viewLifecycleOwner, ::handleGetDataResult)
        viewModel.loadStateObservable.observe(viewLifecycleOwner){
            when(it){
                StateResult.isLoading -> {
                    progressBarLoad.setVisible()
                    textViewErrorMessage.setGone()
                    recyclerViewWatchList.setGone()
                }
                else -> {
                    onFinishLoadData()
                }
            }
        }
    }

    private fun handleGetDataResult(result: DataResult<List<CryptoModelEnt>>) {
        result.handleResult(
            { data ->
                //stopShimmer()
                viewModel.cryptoList.addAll(data)
                watchListAdapter.submitList(viewModel.cryptoList)
                onFinishLoadData()
                recyclerViewWatchList.setVisible()
                viewModel.incrementPageNumber()
                Log.d("errorLog","Data length "+data.size.toString())
            }, {
//                stopShimmer()
                onFinishLoadData()
                Log.d("errorLog", it.exception)
                val view = requireActivity().findViewById(android.R.id.content) as View
                view.showErrorSnackbar(it.exception)
            }
        )
    }

    private fun onFinishLoadData() {
        progressBarLoad.hideSlideDown()
        swipeRefreshLayout.isRefreshing = false
        textViewErrorMessage.showIf { viewModel.cryptoList.isNullOrEmpty() }
    }

}