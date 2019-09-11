package com.pd.shaadi.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pd.shaadi.R
import com.pd.shaadi.Util.ConnectionLiveData
import com.pd.shaadi.adapter.PaginationScrollListener
import com.pd.shaadi.adapter.ProfileDataAdapter
import com.pd.shaadi.databinding.ActivityMainBinding
import com.pd.shaadi.model.database.DbData
import com.pd.shaadi.viewmodel.MatchesViewModel
import com.pd.wifilogging.utils.LoadMoreData
import com.pd.wifilogging.utils.showAlert
import dagger.android.AndroidInjection
import javax.inject.Inject


class MainActivity : AppCompatActivity(), ProfileDataAdapter.MatchProfileClickListener {

    @Inject
    lateinit var viewModel: MatchesViewModel
    private lateinit var binding: ActivityMainBinding
    var isLastPage: Boolean = false
    var isLoading: Boolean = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.matchesviewModel = viewModel

        val connectionLiveData = ConnectionLiveData(applicationContext)
        connectionLiveData.observe(this, Observer<Boolean> { isConnected ->
            isConnected?.let {
                isInternetConnected = it
                if (isInternetConnected) LoadMoreData() else showAlert()
            }
        })

        val adapter = ProfileDataAdapter(this)
        binding.matchesRecylerview.adapter = adapter

        val linearLayoutManager =
            binding.matchesRecylerview.getLayoutManager() as LinearLayoutManager

        viewModel.getProfiledataResult.observe(this, Observer { list ->
            list?.let {
                adapter.submitList(it)
            }
        })

        binding.matchesRecylerview.addOnScrollListener(object :
            PaginationScrollListener(linearLayoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                if (isInternetConnected) LoadMoreData() else showAlert()
            }
        })
        binding.lifecycleOwner = this
    }

    override fun accept(dbData: DbData) {
        viewModel.acceptProfile(dbData.id)
    }

    override fun reject(dbData: DbData) {
        viewModel.rejectProfile(dbData.id)
    }

    companion object{
        var isInternetConnected: Boolean = false
    }

}

