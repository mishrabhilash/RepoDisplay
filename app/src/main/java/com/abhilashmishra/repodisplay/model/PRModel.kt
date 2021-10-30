package com.abhilashmishra.repodisplay.model

data class PRModel(
    val title: String,
    val createdAt: String,
    val closedAt: String,
    val user: GithubUser
)