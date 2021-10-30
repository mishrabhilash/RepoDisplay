package com.abhilashmishra.repodisplay.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhilashmishra.repodisplay.R
import com.abhilashmishra.repodisplay.model.PRModel

class PRListAdapter(private val arrayList: List<PRModel>) : RecyclerView.Adapter<ClosedPRViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClosedPRViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_pull_request, parent, false)
        return ClosedPRViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClosedPRViewHolder, position: Int) {
        holder.populateView(arrayList[position])
    }

    override fun getItemCount(): Int = arrayList.size
}