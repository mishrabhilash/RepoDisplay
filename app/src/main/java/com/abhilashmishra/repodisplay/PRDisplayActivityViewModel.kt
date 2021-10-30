package com.abhilashmishra.repodisplay

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abhilashmishra.repodisplay.model.GithubUser
import com.abhilashmishra.repodisplay.model.PRModel

class PRDisplayActivityViewModel : ViewModel() {
    val prList: MutableLiveData<List<PRModel>> by lazy {
        MutableLiveData<List<PRModel>>()
    }

    fun fetchPage(pageCount: Int) {
        val user = GithubUser("abhilash", "https://avatars.githubusercontent.com/u/8214632?v=4")
        val prModel = PRModel("updated dependencies", "2021-10-29T20:18:17Z", "2021-10-29T20:00:09Z", user)
        val list = ArrayList<PRModel>()
        list.add(prModel)
        list.add(prModel)
        list.add(prModel)
        list.add(prModel)
        prList.value = list
    }
}