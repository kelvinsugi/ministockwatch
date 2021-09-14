package com.kelvinsugiarto.ministockwatch.ui.ui.livedatafeed

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kelvinsugiarto.ministockwatch.ui.R
import com.kelvinsugiarto.ministockwatch.ui.ui.main.MainActivity

class LiveDataFeedFragment : Fragment() {

    companion object {
        fun newInstance() = LiveDataFeedFragment()
    }

    private lateinit var viewModel: LiveDataFeedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.livedata_feed_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LiveDataFeedViewModel::class.java)
        (activity as MainActivity).setToolbarTitle("Live Feed")
        // TODO: Use the ViewModel
    }

}