package com.abhilashmishra.repodisplay.model

import com.google.gson.annotations.SerializedName

data class PRModel(
    @SerializedName("title")
    val title: String,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("closed_at")
    val closedAt: String,

    @SerializedName("user")
    val user: GithubUser
)