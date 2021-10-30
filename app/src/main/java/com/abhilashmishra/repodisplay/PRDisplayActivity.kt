package com.abhilashmishra.repodisplay

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhilashmishra.repodisplay.adapter.PRListAdapter
import com.abhilashmishra.repodisplay.state.ErrorState
import com.abhilashmishra.repodisplay.state.ErrorState.*
import com.abhilashmishra.repodisplay.state.ScreenState

class PRDisplayActivity : AppCompatActivity() {
    private val adapter: PRListAdapter by lazy { PRListAdapter(prDisplayActivityViewModel.listItems) }
    private val prDisplayActivityViewModel: PRDisplayActivityViewModel by viewModels()
    private val linearLayoutManager: LinearLayoutManager by lazy { LinearLayoutManager(this) }

    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: View
    lateinit var messageLayout: View
    lateinit var messageImageView: ImageView
    lateinit var messageText: TextView
    lateinit var refreshButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initObservers()
        prDisplayActivityViewModel.fetchPage()
    }

    private fun initViews() {
        initRecyclerView()
        initMessageView()
        progressBar = findViewById(R.id.progress)
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = linearLayoutManager.itemCount
                val lastVisible = linearLayoutManager.findLastVisibleItemPosition()
                val reachedEnd = lastVisible + 4 >= totalItemCount
                if (totalItemCount > 0 && reachedEnd) {
                    prDisplayActivityViewModel.fetchPage()
                }
            }
        })
    }

    private fun initMessageView() {
        messageLayout = findViewById(R.id.messageLayout)
        messageImageView = findViewById(R.id.message_icon)
        messageText = findViewById(R.id.message_text)
        refreshButton = findViewById(R.id.refresh_button)
    }

    private fun initObservers() {
        prDisplayActivityViewModel.prList.observe(this) {
            val previousPosition = prDisplayActivityViewModel.listItems.size
            prDisplayActivityViewModel.listItems.addAll(it)
            adapter.notifyItemRangeInserted(previousPosition, it.size)

            if (prDisplayActivityViewModel.listItems.isEmpty()) {
                prDisplayActivityViewModel.screenState.value = ScreenState.NO_DATA
            } else {
                prDisplayActivityViewModel.screenState.value = ScreenState.SUCCESS_DATA
            }
        }

        prDisplayActivityViewModel.screenState.observe(this) {
            when (it) {
                ScreenState.ERROR_GENERAL -> {
                    messageLayout.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    showMessageLayout(MESSAGE_ERROR_GENERAL)
                }
                ScreenState.ERROR_NO_NETWORK -> {
                    messageLayout.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    showMessageLayout(MESSAGE_ERROR_NO_NETWORK)
                }
                ScreenState.NO_DATA -> {
                    messageLayout.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    showMessageLayout(MESSAGE_NO_DATA)
                }
                ScreenState.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    refreshButton.setOnClickListener { }
                }
                ScreenState.SUCCESS_DATA -> {
                    messageLayout.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun showMessageLayout(errorState: ErrorState) {
        when (errorState) {
            MESSAGE_ERROR_GENERAL -> {
                messageText.text = resources.getString(R.string.error_message_text_general)
                messageImageView.setImageResource(R.drawable.ic_baseline_error_outline_24)
                refreshButton.setOnClickListener {
                    prDisplayActivityViewModel.fetchPage()
                }
            }
            MESSAGE_ERROR_NO_NETWORK -> {
                messageText.text = resources.getString(R.string.error_message_text_network)
                messageImageView.setImageResource(R.drawable.ic_baseline_wifi_off_24)
                refreshButton.setOnClickListener {
                    prDisplayActivityViewModel.fetchPage()
                }
            }
            MESSAGE_NO_DATA -> {
                messageText.text = resources.getString(R.string.error_message_text_no_data)
                messageImageView.setImageResource(R.drawable.git)
                refreshButton.setOnClickListener {
                    prDisplayActivityViewModel.fetchPage()
                }
            }
        }
    }
}