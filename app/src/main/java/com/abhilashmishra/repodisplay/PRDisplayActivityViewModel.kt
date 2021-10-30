package com.abhilashmishra.repodisplay

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.abhilashmishra.repodisplay.model.PRModel
import com.abhilashmishra.repodisplay.state.RepoState
import com.abhilashmishra.repodisplay.network.NetworkApi
import com.abhilashmishra.repodisplay.network.NetworkStateMonitor
import com.abhilashmishra.repodisplay.state.ScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class PRDisplayActivityViewModel(private val app: Application) : AndroidViewModel(app) {

    companion object {
        const val user: String = "mishrabhilash"
        const val repoName: String = "RepoDisplay"
    }

    val listItems: ArrayList<PRModel> by lazy { ArrayList() }
    var pageCount: Int = 1
    var reachedEnd = false

    private val exceptionHandler by lazy {
        CoroutineExceptionHandler { _, exception ->
            val networkMonitor = NetworkStateMonitor(app)
            if (!networkMonitor.isConnected) {
                screenState.value = ScreenState.ERROR_NO_NETWORK
            } else {
                screenState.value = ScreenState.ERROR_GENERAL
            }
        }
    }

    private val networkApi: NetworkApi by lazy {
        NetworkApi()
    }

    val screenState: MutableLiveData<ScreenState> by lazy {
        MutableLiveData<ScreenState>(ScreenState.NO_DATA)
    }

    val prList: MutableLiveData<List<PRModel>> by lazy {
        MutableLiveData<List<PRModel>>(emptyList())
    }

    fun fetchPage() {
        if (screenState.value == ScreenState.LOADING || reachedEnd) {
            return
        }
        screenState.postValue(ScreenState.LOADING)
        viewModelScope.launch(exceptionHandler) {
            val list = networkApi.getAllClients(user, repoName, RepoState.CLOSED.state, 5, pageCount)
            list.body()?.let {
                prList.value = it
                if (it.isNotEmpty()) {
                    pageCount++
                } else {
                    reachedEnd = true
                }
            }
        }
    }
}