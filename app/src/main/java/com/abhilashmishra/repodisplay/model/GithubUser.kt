package com.abhilashmishra.repodisplay.model

import com.google.gson.annotations.SerializedName

data class GithubUser(
    @SerializedName("login")
    val login: String,

    @SerializedName("avatar_url")
    val avatarUrl: String
)