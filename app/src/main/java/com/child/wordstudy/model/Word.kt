package com.child.wordstudy.model

data class Word(
    val id: Int,
    val english: String,
    val chinese: String,
    val pronunciation: String,
    val example: String,
    val imageResId: Int = android.R.drawable.ic_menu_help
)