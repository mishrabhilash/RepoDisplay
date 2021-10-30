package com.abhilashmishra.repodisplay.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abhilashmishra.repodisplay.R
import com.abhilashmishra.repodisplay.model.PRModel
import com.bumptech.glide.Glide

class ClosedPRViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val titleText: TextView = view.findViewById(R.id.titleText)
    private val createdValueText: TextView = view.findViewById(R.id.createdValue)
    private val closedAtValueText: TextView = view.findViewById(R.id.closedValue)
    private val userAvatarImageView: ImageView = view.findViewById(R.id.userAvatar)
    private val userName: TextView = view.findViewById(R.id.userName)
    fun populateView(data: PRModel){
        titleText.text = data.title
        createdValueText.text = data.createdAt
        closedAtValueText.text = data.closedAt
        Glide.with(view)
            .load(data.user.avatar_url)
            .into(userAvatarImageView)
        userName.text = data.user.login
    }
}