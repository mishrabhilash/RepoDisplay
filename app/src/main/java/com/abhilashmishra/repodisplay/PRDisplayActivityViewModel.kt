package com.abhilashmishra.repodisplay

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhilashmishra.repodisplay.model.GithubUser
import com.abhilashmishra.repodisplay.model.PRModel
import com.abhilashmishra.repodisplay.model.RepoState
import com.abhilashmishra.repodisplay.network.NetworkApi
import kotlinx.coroutines.launch

class PRDisplayActivityViewModel : ViewModel() {

    companion object {
        const val user: String = "mishrabhilash"
        const val repoName: String = "RepoDisplay"
    }

    val networkApi: NetworkApi by lazy {
        NetworkApi()
    }

    val prList: MutableLiveData<List<PRModel>> by lazy {
        MutableLiveData<List<PRModel>>()
    }

    fun fetchPage(pageCount: Int) {
        viewModelScope.launch {
            val list = networkApi.getAllClients(user, repoName, RepoState.CLOSED.state, 10, pageCount)
            prList.value = list.body()
        }
    }
}