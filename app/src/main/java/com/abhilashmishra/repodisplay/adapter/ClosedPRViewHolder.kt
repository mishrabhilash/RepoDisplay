package com.abhilashmishra.repodisplay.adapter

import android.text.format.DateUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abhilashmishra.repodisplay.R
import com.abhilashmishra.repodisplay.model.PRModel
import com.abhilashmishra.repodisplay.utility.setCornerRadius
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

class ClosedPRViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val titleText: TextView = view.findViewById(R.id.titleText)
    private val createdValueText: TextView = view.findViewById(R.id.createdValue)
    private val closedAtValueText: TextView = view.findViewById(R.id.closedValue)
    private val userAvatarImageView: ImageView = view.findViewById(R.id.userAvatar)
    private val userName: TextView = view.findViewById(R.id.userName)
    fun populateView(data: PRModel) {
        titleText.text = data.title
        createdValueText.text = data.createdAt.formatDate()
        closedAtValueText.text = data.closedAt.formatDate()
        Glide.with(view)
            .load(data.user.avatarUrl)
            .into(userAvatarImageView)
        userAvatarImageView.setCornerRadius(view.resources.getDimensionPixelSize(R.dimen.dimen_8dp))
        userName.text = data.user.login
    }
}

private fun String?.formatDate(): CharSequence? {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    dateFormat.timeZone = TimeZone.getTimeZone("GMT")
    this?.let { currentString ->
        val time = dateFormat.parse(currentString)?.time
        time?.let {
            val now = System.currentTimeMillis()
            return DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS)
        }
    }
    return null
}


