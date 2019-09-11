package com.pd.shaadi.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pd.shaadi.OpenForTesting
import com.pd.shaadi.repository.AppRepository
import com.pd.shaadicom.model.database.ResponseData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@OpenForTesting
class MatchesViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    private lateinit var disposableObserver: DisposableObserver<ResponseData>

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val getProfiledataResult = appRepository.getDataFromDb()

    fun getData(load: Int) {
        disposableObserver = object : DisposableObserver<ResponseData>() {
            override fun onComplete() {}
            override fun onNext(t: ResponseData) {
                Log.e("MatchesViewModel", t.results.size.toString())
            }

            override fun onError(e: Throwable) {
            }
        }

        appRepository.getDataFromApi(load)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(400, TimeUnit.MILLISECONDS)
            .subscribe(disposableObserver)
    }

    private fun disposeElements() {
        if (!disposableObserver.isDisposed) disposableObserver.dispose()
    }


    fun acceptProfile(id: String) {
        uiScope.launch {
            accept(id)
        }
    }

    suspend fun accept(id: String) {
        withContext(Dispatchers.IO) {
            appRepository.updateProfileaccept(id)
        }
    }

    fun rejectProfile(id: String) {
        uiScope.launch {
            reject(id)
        }
    }

    suspend fun reject(id: String) {
        withContext(Dispatchers.IO) {
            appRepository.updateProfilereject(id)
        }
    }

    //Deleting Test Data
    fun removeTestData(id: String) {
        uiScope.launch {
            delete(id)
        }
    }

    suspend fun delete(id: String) {
        withContext(Dispatchers.IO) {
            appRepository.delete(id)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposeElements()
        viewModelJob.cancel()
    }
}
