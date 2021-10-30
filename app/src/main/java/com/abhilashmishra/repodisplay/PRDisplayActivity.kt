package com.abhilashmishra.repodisplay

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhilashmishra.repodisplay.adapter.PRListAdapter
import com.abhilashmishra.repodisplay.model.PRModel

class PRDisplayActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    val listItems: ArrayList<PRModel> by lazy { ArrayList() }
    val adapter: PRListAdapter by lazy { PRListAdapter(listItems) }
    val prDisplayActivityViewModel: PRDisplayActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initObservers()
        prDisplayActivityViewModel.fetchPage(0)
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun initObservers() {
        prDisplayActivityViewModel.prList.observe(this) {
            val previousPosition = listItems.size
            listItems.addAll(it)
            adapter.notifyItemRangeInserted(previousPosition, it.size)
        }
    }
}